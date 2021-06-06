package com.abcbank.voucherservice.service.rest;

import com.abcbank.voucherservice.model.dto.OrderDTO;
import com.abcbank.voucherservice.model.dto.PurchaseRequest;

import java.util.List;

public interface PurchaseService {
    public String purchaseData(PurchaseRequest request, String requestId);

    public List<OrderDTO> purchaseList(String phoneNumber, String requestId);

    public String getVoucherCode(Long orderId, String phoneNumber, String telco,String price, String dataPlan, String requestId);
}
