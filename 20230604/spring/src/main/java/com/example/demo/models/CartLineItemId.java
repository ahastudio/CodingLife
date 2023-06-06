package com.example.demo.models;

public class CartLineItemId extends EntityId {
    private CartLineItemId() {
        super();
    }

    public CartLineItemId(String value) {
        super(value);
    }

    public static CartLineItemId generate() {
        return new CartLineItemId(newTsid());
    }
}
