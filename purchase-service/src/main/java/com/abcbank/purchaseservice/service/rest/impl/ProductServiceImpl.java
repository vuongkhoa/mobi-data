package com.abcbank.purchaseservice.service.rest.impl;

import com.abcbank.purchaseservice.model.dto.ProductDTO;
import com.abcbank.purchaseservice.repository.mysql.ProductRepository;
import com.abcbank.purchaseservice.service.rest.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProduct() {
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDTO> entityToDto = modelMapper.map(productRepository.findAll(), new TypeToken<List<ProductDTO>>(){}.getType());
        return entityToDto;
    }
}
