package com.abcbank.voucherservice.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@ConfigurationProperties(prefix = "endpoint")
@Getter
@Setter
@Configuration
@Slf4j
public class EndpointConfig {

    private int msTimeoutMs = 5000;
    // Microservices
    private String internalApiUrl;
    private String getVoucherUri;

    //Use for service create re issue card
    @Bean(name="internalApiUrl")
    public WebClient internalApiUrl() {
        log.info("internalApiUrl: {}", getInternalApiUrl());
        return WebClient.builder().baseUrl(getInternalApiUrl()).build();
    }
}
