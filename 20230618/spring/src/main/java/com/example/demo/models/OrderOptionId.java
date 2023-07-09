package com.example.demo.models;

public class OrderOptionId extends EntityId {
    private OrderOptionId() {
        super();
    }

    public OrderOptionId(String value) {
        super(value);
    }

    public static OrderOptionId generate() {
        return new OrderOptionId(newTsid());
    }
}
