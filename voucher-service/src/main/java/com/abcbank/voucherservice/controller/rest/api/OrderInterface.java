package com.abcbank.voucherservice.controller.rest.api;


import com.abcbank.voucherservice.model.dto.OrderDTO;
import com.abcbank.voucherservice.model.dto.PurchaseRequest;
import com.abcbank.voucherservice.model.dto.VoucherResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/")
public interface OrderInterface {

    @GetMapping(value = "vouchers")
    @ApiResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            ))
    ResponseEntity getPurchaseList(@RequestParam(value = "phoneNumber", required = true) String phoneNumber,
                                   @RequestHeader(value = "X-Request-Id", required = true) String requestId);

    @PostMapping(value = "voucher")
    @ApiResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            ))
    Mono<VoucherResponse> purchase(@RequestHeader(value = "X-Request-Id", required = true) String requestId,
                                   @Valid @RequestBody PurchaseRequest request);

  @GetMapping(value = "test")
  @ApiResponse(responseCode = "200",
      content = @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE
      ))
  ResponseEntity testApi();

}
