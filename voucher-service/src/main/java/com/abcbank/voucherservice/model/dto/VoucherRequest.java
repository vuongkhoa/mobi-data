package com.abcbank.voucherservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoucherRequest {
  public String partnerId;
  public String requestId;
  public String price;
  public String dataPlan;
  public String telco;
}
