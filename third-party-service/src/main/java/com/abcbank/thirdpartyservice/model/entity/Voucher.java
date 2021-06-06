package com.abcbank.thirdpartyservice.model.entity;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;


@Entity(name = "voucher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Voucher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String status; //USED/NEW
  String price;
  String telco; //Vinaphone/Viettel/Mobifone
  @Column(name = "data_plan")
  String dataPlan;//2GB/3GB/5GB per month
  String code;
  Instant createdDate;
  Instant updatedDate;

}
