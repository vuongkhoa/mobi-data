package com.abcbank.purchaseservice.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatusCodeEnum {

    /**
     * response codes should follow the standard: XXXYYYY where
     * - XXX: application shortname
     * - YYYY: numeric code of the error code
     */

    SUCCESS("200", 200),
    BUSINESS_ERROR("ABC0001", 200),
    VALIDATION_ERROR("ABC0002", 400),
    IO_EXCEPTION_ERROR("ABC0004", 400),
    INTERNAL_GENERAL_SERVER_ERROR("ABC0003", 500);
    // Adds more response codes here

    private String code;
    private int httpCode;

    @Override
    public String toString() {
        return "ResponseStatus{" +
                "code='" + code + '\'' +
                "httpCode='" + httpCode + '\'' +
                '}';
    }
}
