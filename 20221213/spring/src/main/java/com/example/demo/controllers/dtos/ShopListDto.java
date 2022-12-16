package com.example.demo.controllers.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ShopListDto {
    private List<Shop> shops;

    private ShopListDto() {
    }

    @Builder
    public ShopListDto(List<Shop> shops) {
        this.shops = shops;
    }

    @Getter
    public static class Shop {
        private String id;
        private String name;
    }
}
