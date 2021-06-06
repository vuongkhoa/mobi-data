package com.abcbank.voucherservice.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrackingContextEnum {

    //clientIP for tracking client
    //correlationID for tracking transaction in order to track fund transfer transaction in message queue system
    X_FORWARD_FOR("x-forwarded-for", "clientIP"),
    X_CORRELATION_ID("X-Correlation-ID", "correlationID");

    private final String headerKey;
    private final String threadKey;
}
