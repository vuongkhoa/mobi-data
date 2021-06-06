package com.abcbank.purchaseservice.common.factory.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.abcbank.purchaseservice.locale.Translator;

import java.io.Serializable;


public class ResponseStatusTest implements Serializable {

    public ResponseStatusTest(String code, boolean setMessageImplicitly) {
        setCode(code, setMessageImplicitly);
    }

    private String code;

    /**
     * Set the code. this will implicitly set the message based on the locale
     *
     * @param code
     */
    public void setCode(String code) {
        setCode(code, true);
    }

    /**
     * Set the code
     *
     * @param code
     */
    public void setCode(String code, boolean setMessageImplicitly) {
        this.code = code;
        if (setMessageImplicitly) {
            this.message = Translator.toLocale(code);
        }
    }

    public String getCode() {
        return code;
    }

    @JsonProperty("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
