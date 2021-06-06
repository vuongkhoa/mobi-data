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
        value = HttpStatus.INTERNAL_SERVER_ERROR,
        reason = "")
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -8252928424894283431L;

    private String code;
    private String message;
    private String reason;
}
