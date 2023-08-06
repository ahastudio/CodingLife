package com.example.demo.dtos;

import java.util.List;

public record CartDto(
        List<LineItem> lineItems,
        long totalPrice
) {
    public record LineItem(
            String id,
            Product product,
            List<Option> options,
            long unitPrice,
            int quantity,
            long totalPrice
    ) {
    }

    public record Product(
            String id,
            Image thumbnail,
            String name
    ) {
    }

    public record Image(
            String url
    ) {
    }

    public record Option(
            String name,
            OptionItem item
    ) {
    }

    public record OptionItem(
            String name
    ) {
    }
}
