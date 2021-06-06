package com.abcbank.voucherservice.common.util;

import java.sql.Timestamp;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;


@Slf4j
public class DataUtils {


    public static Boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean nullObject(Object object) {
        return object == null;
    }

    public static String parseToString(Object obj) {
        if (isNull(obj)) {
            return null;
        }
        return String.valueOf(obj);
    }


    public static Long parseToLong(Object obj) {
        if (isNull(obj)) {
            return null;
        }
        return Long.parseLong(parseToString(obj));
    }


    public static String formatPhoneNumber(String isdn) {
        if (isdn.startsWith("84")) {
            return isdn;
        } else if (isdn.startsWith("+84")) {
            return isdn.substring(1);
        } else if (isdn.startsWith("0")) {
            isdn = isdn.substring(1);
        }
        return String.format("84%s", isdn);
    }


    public static String randomNumberByDate() {
        return String.format("%s%s", RandomStringUtils.randomNumeric(8), System.nanoTime());
    }


    /**
     * Check format number phone type 0123 456 789
     * 
     * @param dataInput
     * @return
     */
    public static boolean isCheckNumberFormat(String dataInput) {
        
        String str = "\\d{1}\\d{3} \\d{3} \\d{4}";

        return Pattern.compile(str).matcher(dataInput).matches();
    }


    public static long getRandomNumber() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }
}
