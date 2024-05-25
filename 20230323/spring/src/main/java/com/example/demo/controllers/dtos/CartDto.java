package com.example.demo.controllers.dtos;

import java.util.List;

import jakarta.persistence.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("carts")
public class CartDto {
    @Id
    private String id;

    private List<LineItemDto> lineItems;

    @TimeToLive
    private Long expiration;

    private CartDto() {
    }

    public CartDto(String id, List<LineItemDto> lineItems) {
        this.id = id;
        this.lineItems = lineItems;
    }

    public List<LineItemDto> getLineItems() {
        return lineItems;
    }

    public record LineItemDto(
            String id,
            String productName,
            long unitPrice,
            int quantity,
            long totalPrice
    ) {
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id='" + id + '\'' +
                ", lineItems=" + lineItems +
                '}';
    }

    /**
     * Set the expiration time.
     *
     * @param seconds expiration time in seconds
     */
    public void setExpiration(Long seconds) {
        this.expiration = seconds;
    }
}
