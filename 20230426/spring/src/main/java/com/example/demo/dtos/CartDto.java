package com.example.demo.dtos;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("carts")
public class CartDto {
    @Id
    private String id;

    private List<LineItemDto> lineItems;

    private CartDto() {
    }

    public CartDto(String id, List<LineItemDto> lineItems) {
        this.id = id;
        this.lineItems = lineItems;
    }

    public List<LineItemDto> getLineItems() {
        return lineItems;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id='" + id + '\'' +
                ", lineItems=" + lineItems +
                '}';
    }

    public record LineItemDto(
            String id,
            String productName,
            long unitPrice,
            int quantity,
            long totalPrice
    ) {
    }
}
