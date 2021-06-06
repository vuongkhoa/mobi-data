package com.abcbank.voucherservice.service.rest.impl;

import com.abcbank.voucherservice.model.entity.Order;
import com.abcbank.voucherservice.repository.mysql.OrderRepository;
import com.abcbank.voucherservice.service.rest.OnEventListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OnEventListenerImpl implements OnEventListener {

    private final OrderRepository orderRepository;

    @Override
    public void onEvent(Long orderId, String voucherCode)
    {
        Optional<Order> orderOP = orderRepository.findById(orderId);
        if(orderOP.isPresent()){
            Order or = orderOP.get();
            or.setVoucherCode(voucherCode);
            or.setUpdatedDate(Instant.now());
            or.setStatus("COMPLETED");
            orderRepository.save(or);
        }
    }
}
