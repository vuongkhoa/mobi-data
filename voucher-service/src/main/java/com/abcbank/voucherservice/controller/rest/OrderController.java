package com.abcbank.voucherservice.controller.rest;

import com.abcbank.voucherservice.common.factory.response.ResponseFactory;
import com.abcbank.voucherservice.controller.rest.api.OrderInterface;
import com.abcbank.voucherservice.exceptions.BusinessException;
import com.abcbank.voucherservice.exceptions.SystemException;
import com.abcbank.voucherservice.model.dto.OrderDTO;
import com.abcbank.voucherservice.model.dto.PurchaseRequest;
import com.abcbank.voucherservice.model.dto.VoucherResponse;
import com.abcbank.voucherservice.service.rest.PurchaseService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class OrderController implements OrderInterface {
    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private PurchaseService purchaseService;

    @Override
    public ResponseEntity getPurchaseList(String phoneNumber, String requestId) {
        List<OrderDTO> orderDTOList = purchaseService.purchaseList(phoneNumber, requestId);
        return responseFactory.success(orderDTOList, ArrayList.class);
    }

    @Override
    public Mono<VoucherResponse> purchase(String requestId, @Valid PurchaseRequest request) {
        String voucher = null;
        String message = null;
        try {
            voucher = purchaseService.purchaseData(request, requestId);
            message = "Success";
        } catch(BusinessException e){
            message = e.getMessage();
        } catch(SystemException ex){
            return Mono.error(ex);
        }

        VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse.setCode(voucher);
        voucherResponse.setMessage(message);

        return Mono.just(voucherResponse);
    }

    @GetMapping(value = "test")
    public ResponseEntity testApi() {
        log.info("Testing...");
        return responseFactory.success("Hello Voucher tester", String.class);
    }

}
