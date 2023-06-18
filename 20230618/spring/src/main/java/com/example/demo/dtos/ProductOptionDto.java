package com.example.demo.dtos;

import java.util.List;
import java.util.stream.IntStream;

import com.example.demo.models.ProductOption;

public record ProductOptionDto(
        String id,
        String name,
        List<ProductOptionItemDto> items
) {
    public static ProductOptionDto of(ProductOption option) {
        return new ProductOptionDto(
                option.id().toString(),
                option.name(),
                IntStream.range(0, option.itemSize())
                        .mapToObj(index ->
                                ProductOptionItemDto.of(option.item(index)))
                        .toList()
        );
    }
}
