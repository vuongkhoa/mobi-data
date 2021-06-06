package com.abcbank.purchaseservice.controller.rest;

import java.util.ArrayList;
import java.util.List;
import com.abcbank.purchaseservice.common.factory.response.ResponseFactory;
import com.abcbank.purchaseservice.controller.rest.api.ProductInterface;
import com.abcbank.purchaseservice.model.dto.ProductDTO;
import com.abcbank.purchaseservice.service.rest.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements ProductInterface {
    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity getProductList(String requestId) {
        List<ProductDTO> productDTOList = productService.getAllProduct();
        return responseFactory.success(productDTOList, ArrayList.class);
    }
}
