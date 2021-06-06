package com.abcbank.thirdpartyservice.utils;

import java.sql.Timestamp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataUtils {

  public static String getRandomNumber() {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    return String.valueOf(timestamp.getTime());
  }

}
