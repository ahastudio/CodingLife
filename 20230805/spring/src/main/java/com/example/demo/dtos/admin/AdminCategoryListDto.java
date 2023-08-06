package com.example.demo.dtos.admin;

import java.util.List;

import com.example.demo.models.Category;

public record AdminCategoryListDto(
        List<CategoryDto> categories
) {
    public static AdminCategoryListDto of(List<Category> categories) {
        return new AdminCategoryListDto(
                categories.stream()
                        .map(category -> new CategoryDto(
                                category.id().toString(),
                                category.name(),
                                category.hidden()
                        ))
                        .toList()
        );
    }

    public record CategoryDto(
            String id,
            String name,
            boolean hidden
    ) {
    }
}
