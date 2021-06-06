package com.abcbank.thirdpartyservice.service.impl;

import com.abcbank.thirdpartyservice.model.entity.Voucher;
import com.abcbank.thirdpartyservice.repository.mysql.VoucherRepository;
import com.abcbank.thirdpartyservice.service.VoucherService;
import com.abcbank.thirdpartyservice.utils.DataUtils;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoucherServiceImpl implements VoucherService {

  private final VoucherRepository voucherRepository;
  private static int count = 0;
  int delayTime = 6; //seconds

  @Override
  public String getVoucher(String partnerId, String requestId, String telco, String price, String dataPlan) {

    String voucher = DataUtils.getRandomNumber();

    if (isDelay()) {
        try {
          Thread.sleep((long) (delayTime * 1000));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
    }

    //TODO: demo only
    //create new voucher (this could be get voucher from DB)
    Voucher voucher1 = new Voucher();
    voucher1.setTelco(telco);
    voucher1.setPrice(price);
    voucher1.setDataPlan(dataPlan);
    voucher1.setCode(voucher);
    voucher1.setStatus("USED");
    voucher1.setCreatedDate(Instant.now());
    voucher1.setUpdatedDate(Instant.now());

    voucherRepository.save(voucher1);

    return voucher;
  }

  private boolean isDelay() {
    count ++;
    if (count > 1000) count = 0;
    return count %2 == 0;
  }

}
