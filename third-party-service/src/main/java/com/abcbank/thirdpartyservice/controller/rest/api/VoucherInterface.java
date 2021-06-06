package com.abcbank.thirdpartyservice.controller.rest.api;

import com.abcbank.thirdpartyservice.model.VoucherRequest;
import com.abcbank.thirdpartyservice.model.VoucherResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;


@RequestMapping("/api/v1/")
public interface VoucherInterface {

  @GetMapping(value = "test")
  @ApiResponse(responseCode = "200",
      content = @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE
      ))
  String testApi();


  @PostMapping(value = "voucher")
  @ApiResponse(responseCode = "200",
      content = @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE
      ))
  Mono<VoucherResponse> createVoucher(@RequestBody VoucherRequest request);

}
