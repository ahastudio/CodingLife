package com.ahastudio.demo.shop.controllers.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductListDto {
    private List<Product> products;

    private ProductListDto() {
    }

    @Builder
    public ProductListDto(List<Product> products) {
        this.products = products;
    }

    @Getter
    public static class Product {
        private String id;
        private String name;
        private Long price;
    }
}
