package com.example.demo.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartId extends EntityId {
    // TODO: delete this! :)
    public static final CartId DEFAULT = new CartId("0BV000000CART");

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
