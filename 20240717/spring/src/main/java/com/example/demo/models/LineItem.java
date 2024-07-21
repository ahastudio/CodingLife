package com.example.demo.models;

import java.util.Objects;

public class LineItem {
    private Long productId;
    private int quantity;

    public LineItem(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public boolean sameProduct(Long productId) {
        return Objects.equals(productId, this.productId);
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public Long productId() {
        return productId;
    }

    public int quantity() {
        return quantity;
    }
}
