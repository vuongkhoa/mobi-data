package com.abcbank.purchaseservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    Long id;
    String telco;
    String data_plan;
    String price;
    String type;
    String status;

    public ProductDTO(Long id, String telco, String data_plan, String price) {
        this.id = id;
        this.telco = telco;
        this.data_plan = data_plan;
        this.price = price;
    }
}
