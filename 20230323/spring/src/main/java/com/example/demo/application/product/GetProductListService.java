package com.example.demo.application.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controllers.dtos.ProductListDto;
import com.example.demo.infrastructure.ProductDtoFetcher;

@Service
@Transactional(readOnly = true)
public class GetProductListService {
    private final ProductDtoFetcher productDtoFetcher;

    public GetProductListService(ProductDtoFetcher productDtoFetcher) {
        this.productDtoFetcher = productDtoFetcher;
    }

    public ProductListDto getProductListDto() {
        return productDtoFetcher.fetchProductListDto();
    }
}
