package com.ahastudio.demo.shop.controllers.dtos;

import java.util.List;

public record ProductListDto(
        List<Product> products
) {
    public record Product(
            String id,
            Category category,
            Image thumbnail,
            String name,
            Long price
    ) {
    }

    public record Category(
            String id,
            String name
    ) {
    }

    public record Image(
            String url
    ) {
    }
}
