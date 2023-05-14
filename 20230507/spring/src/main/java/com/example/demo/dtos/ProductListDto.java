package com.example.demo.dtos;

import java.util.List;

public record ProductListDto(
        List<ProductSummaryDto> products
) {
}
