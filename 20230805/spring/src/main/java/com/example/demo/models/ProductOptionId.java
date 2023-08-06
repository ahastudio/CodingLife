package com.example.demo.models;

public class ProductOptionId extends EntityId {
    private ProductOptionId() {
        super();
    }

    public ProductOptionId(String value) {
        super(value);
    }

    public static ProductOptionId generate() {
        return new ProductOptionId(newTsid());
    }
}
