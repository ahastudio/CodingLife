package com.example.demo.models;

public class CartId extends EntityId {
    private CartId() {
        super();
    }

    public CartId(String value) {
        super(value);
    }

    public static CartId generate() {
        return new CartId(newTsid());
    }
}
