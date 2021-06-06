package com.abcbank.voucherservice.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"stackTrace", "cause", "localizedMessage", "suppressed"})
@ToString
@ResponseStatus(
        value = HttpStatus.UNPROCESSABLE_ENTITY,
        reason = "Can not get voucher in time!. We will send SMS for you very soon")
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -8252928424894283431L;

    private String code;
    private String message;
    private String reason;
}
