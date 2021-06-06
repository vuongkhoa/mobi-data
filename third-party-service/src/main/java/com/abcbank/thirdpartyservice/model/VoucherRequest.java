package com.abcbank.thirdpartyservice.model;

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
  public String telco;
  public String price;
  public String dataPlan;
}
