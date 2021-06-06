package com.abcbank.voucherservice.service.rest.impl;

import com.abcbank.voucherservice.common.util.DataUtils;
import com.abcbank.voucherservice.common.util.MD5;
import com.abcbank.voucherservice.config.EndpointConfig;
import com.abcbank.voucherservice.exceptions.BusinessException;
import com.abcbank.voucherservice.exceptions.SystemException;
import com.abcbank.voucherservice.model.dto.*;
import com.abcbank.voucherservice.model.entity.Order;
import com.abcbank.voucherservice.model.entity.User;
import com.abcbank.voucherservice.repository.mysql.OrderRepository;
import com.abcbank.voucherservice.repository.mysql.UserRepository;
import com.abcbank.voucherservice.service.rest.OnEventListener;
import com.abcbank.voucherservice.service.rest.PurchaseService;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    @Autowired
    private OnEventListener mListener;

    @Autowired
    private EndpointConfig endpointConfig;

    @Autowired
    private WebClient internalApiUrl;

    @Override
    public String purchaseData(PurchaseRequest request, String requestId) {
        //check User exist, if not create new one
        UserDTO user = this.checkUserExist(request.getPhoneNumber());
        //create new order request
        Order order = new Order();
        order.setPhoneNumber(MD5.getMd5(user.getPhoneNumber()));
        order.setStatus("INIT");
        order.setCreatedDate(Instant.now());
        order.setUserId(user.getId());
        order.setRequestId(requestId);
        order.setTelco(request.getTelco());
        order.setDataPlan(request.getDataPlan());
        order.setPrice(request.getPrice());
        order = orderRepository.save(order);

        //create new thread to get voucher
        String voucher = this.getVoucherCode(order.getId(), user.getPhoneNumber(),
                    request.getTelco(), request.getPrice(), request.getDataPlan(), requestId);
        return voucher;
    }

    @Override
    public List<OrderDTO> purchaseList(String phoneNumber, String requestId) {
        List<Order> orderDTOList = orderRepository.findByPhoneNumber(MD5.getMd5(phoneNumber));
        ModelMapper modelMapper = new ModelMapper();
        List<OrderDTO> entityToDto = modelMapper.map(orderDTOList, new TypeToken<List<OrderDTO>>(){}.getType());
        return entityToDto;
    }


    private UserDTO checkUserExist(String phoneNumber){
        UserDTO userDTO = null;
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (DataUtils.nullObject(user)) {
            // create new one
            user = new User();
            user.setPhoneNumber(phoneNumber);
            user.setCreatedDate(Instant.now());
            user.setStatus("ACTIVE");
            user = userRepository.save(user);
        }
        ModelMapper modelMapper = new ModelMapper();
        userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    // My Asynchronous task
    public String getVoucherCode(Long orderId, String phoneNumber, String telco,
                                 String price, String dataPlan, String requestId){
        log.info("get Voucher Code from 3rd service -- info requestId: {} ", requestId);
        VoucherRequest voucherRequest = new VoucherRequest();
        voucherRequest.setPartnerId(phoneNumber);
        voucherRequest.setRequestId(requestId);
        voucherRequest.setPrice(price);
        voucherRequest.setDataPlan(dataPlan);
        voucherRequest.setTelco(telco);
        // perform any operation
        Mono<VoucherResponse> result = internalApiUrl
                .post()
                .uri(endpointConfig.getInternalApiUrl()+endpointConfig.getGetVoucherUri())
                .bodyValue(voucherRequest)
                .exchange()
                .timeout(Duration.ofMillis(endpointConfig.getMsTimeoutMs()), Mono.error(new BusinessException("BU_01", "Can not get voucher in time!. We will send SMS for you very soon", "third-party-service")))
                .flatMap(clientResponse -> {
                if (clientResponse.statusCode().is2xxSuccessful()) {
                    return clientResponse.bodyToMono(VoucherResponse.class);
                }
                log.error("Can not get voucher! with error code BU_02 -- info requestId: {} ", requestId);
                return Mono.error(new SystemException("BU_02", "Can not get voucher!", "third-party-service"));
            });

        VoucherResponse voucher = result.block();
        if (mListener != null) {
           // invoke the callback method to save voucher
            mListener.onEvent(orderId, voucher.getCode());
        }
        log.info("Voucher Code result: {} ", voucher.getCode());
        return voucher.getCode();
    }

}
