package com.abcbank.voucherservice.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public final class Constants {

  public static class COMMON_STATUS {
    public static final Integer ACTIVE = 1;
    public static final Integer INACTIVE = 0;
  }

  public static class PROCESS_STATUS {
    public static final String INITIAL = "INITIAL";
    public static final String PROCESSING = "PROCESSING";
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final String TIMEOUT = "TIMEOUT";
  }

  public static final class PAGE {
    public static final Long MIN_PAGE_NUMBER = 1L;
    public static final Long MIN_PAGE_SIZE = 1L;
    public static final Long MAX_PAGE_SIZE = 100L;
  }

}

