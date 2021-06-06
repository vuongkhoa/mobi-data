package com.abcbank.voucherservice.config;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config implements WebMvcConfigurer {
    @Value("${application.allowedOrigins}")
    String allowedOrigins;

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins(allowedOrigins)
//                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS","PATCH")
//                .allowedHeaders("*");

//        if(allowedOrigins != null && !allowedOrigins.isEmpty()){
//            registry.addMapping("/**")
//                    .allowedOrigins(allowedOrigins.split(","))
//                    .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS","PATCH")
//                    .allowedHeaders("*");
//        }else{
//            registry.addMapping("/**")
//                    .allowedOrigins(allowedOrigins)
//                    .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS","PATCH")
//                    .allowedHeaders("*");
//        }
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.abcbank.voucherservice.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo())
                .directModelSubstitute(Timestamp.class, LocalDateTime.class);
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Purchase Voucher Service")
                .description("Purchase sim data Service")
                .contact(new Contact("ABC - ", "https://abcbank.com", "info@abcbank.com"))
                .version("1.0.0")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
