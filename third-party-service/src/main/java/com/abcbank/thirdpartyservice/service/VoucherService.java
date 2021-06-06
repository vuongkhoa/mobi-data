package com.abcbank.thirdpartyservice.service;

public interface VoucherService {
  String getVoucher(String partnerId, String requestId, String telco, String price, String dataPlan);
}
