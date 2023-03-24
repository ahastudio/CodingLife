package com.example.demo.controllers.dtos;

import java.util.List;

public class ProductListDto {
    private List<ProductDto> products;

    public ProductListDto(List<ProductDto> products) {
        this.products = products;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public record ProductDto(
            String id,
            String name,
            Long price
    ) {
    }
}
