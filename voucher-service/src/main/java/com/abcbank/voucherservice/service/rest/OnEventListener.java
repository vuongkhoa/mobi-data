package com.abcbank.voucherservice.service.rest;

public interface OnEventListener {
    void onEvent(Long orderId, String voucherCode);
}
