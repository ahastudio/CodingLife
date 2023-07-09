package com.example.demo.models;

public class OrderId extends EntityId {
    private OrderId() {
        super();
    }

    public OrderId(String value) {
        super(value);
    }

    public static OrderId generate() {
        return new OrderId(newTsid());
    }
}
