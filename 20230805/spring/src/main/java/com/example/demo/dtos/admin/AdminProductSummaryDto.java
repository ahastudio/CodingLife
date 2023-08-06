package com.example.demo.dtos.admin;

import com.example.demo.dtos.CategoryDto;
import com.example.demo.dtos.ImageDto;
import com.example.demo.models.Category;
import com.example.demo.models.Product;

public record AdminProductSummaryDto(
        String id,
        CategoryDto category,
        ImageDto thumbnail,
        String name,
        Long price,
        boolean hidden
) {
    public static AdminProductSummaryDto of(
            Product product, Category category) {
        return new AdminProductSummaryDto(
                product.id().toString(),
                CategoryDto.of(category),
                ImageDto.of(product.image(0)),
                product.name(),
                product.price().asLong(),
                product.hidden()
        );
    }
}
