package com.abcbank.thirdpartyservice.controller;

import com.abcbank.thirdpartyservice.controller.rest.api.VoucherInterface;
import com.abcbank.thirdpartyservice.model.VoucherRequest;
import com.abcbank.thirdpartyservice.model.VoucherResponse;
import com.abcbank.thirdpartyservice.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class VoucherController implements VoucherInterface {

  @Autowired
  private VoucherService voucherService;

  @Override
  public Mono<VoucherResponse> createVoucher(
        @RequestBody VoucherRequest request){

    String voucher = voucherService.getVoucher(request.getRequestId(), request.getPartnerId(), request.getTelco(), request.getPrice(), request.getDataPlan());
    VoucherResponse voucherResponse = new VoucherResponse();
    voucherResponse.setCode(voucher);

    return Mono.just(voucherResponse);

  }

  @Override
  public String testApi() {
    return "Hello tester";
  }
}
