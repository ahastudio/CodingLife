package com.example.demo.dtos.admin;

import java.util.List;

public record AdminProductListDto(
        List<AdminProductSummaryDto> products
) {
}
