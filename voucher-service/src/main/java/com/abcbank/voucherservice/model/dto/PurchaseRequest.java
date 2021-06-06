package com.abcbank.voucherservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
  public String phoneNumber;
  String telco;
  String price;
  String dataPlan;
}
