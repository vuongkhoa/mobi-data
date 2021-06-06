package com.abcbank.purchaseservice.common.factory.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.abcbank.purchaseservice.locale.Translator;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseStatus implements Serializable {

    public ResponseStatus(String code, boolean setMessageImplicitly) {
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
        this.displayMessage = this.message;
    }

    public void editCode(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @JsonProperty("message")
    private String message;

    @JsonProperty("displayMessage")
    private String displayMessage;
}
