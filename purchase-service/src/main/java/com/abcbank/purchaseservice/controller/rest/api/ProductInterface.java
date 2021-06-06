package com.abcbank.purchaseservice.controller.rest.api;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/")
public interface ProductInterface {

    @GetMapping(value = "products")
    @ApiResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            ))
    ResponseEntity getProductList(@RequestHeader(value = "X-Request-Id", required = true) String requestId);

}
