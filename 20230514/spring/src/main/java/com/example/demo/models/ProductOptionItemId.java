package com.example.demo.models;

public class ProductOptionItemId extends EntityId {
    private ProductOptionItemId() {
        super();
    }

    public ProductOptionItemId(String value) {
        super(value);
    }

    public static ProductOptionItemId generate() {
        return new ProductOptionItemId(newTsid());
    }
}
