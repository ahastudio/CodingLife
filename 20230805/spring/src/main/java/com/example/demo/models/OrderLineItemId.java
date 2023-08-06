package com.example.demo.models;

public class OrderLineItemId extends EntityId {
    private OrderLineItemId() {
        super();
    }

    public OrderLineItemId(String value) {
        super(value);
    }

    public static OrderLineItemId generate() {
        return new OrderLineItemId(newTsid());
    }
}
