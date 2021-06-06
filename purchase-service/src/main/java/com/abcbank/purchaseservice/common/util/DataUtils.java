package com.abcbank.purchaseservice.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;

@Slf4j
public class DataUtils {

    private final static String USER_INFO_CONTEXT = "USER_DATA";


    public static String objectToJson(Object data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }

    public static <T> T jsonToObject(String jsonData, Class<T> classOutput)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // TypeFactory typeFactory = mapper.getTypeFactory();
        return mapper.readValue(jsonData, classOutput);
    }


    public static String randomNumberByDate() {
        return String.format("%s%s", RandomStringUtils.randomNumeric(8), System.nanoTime());
    }


}
