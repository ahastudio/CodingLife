package com.example.demo.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class OrderOptionItem {
    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "product_option_item_id")
    )
    private ProductOptionItemId productOptionItemId;

    @Column(name = "product_option_item_name")
    private String name;

    private OrderOptionItem() {
    }

    public OrderOptionItem(ProductOptionItem productOptionItem) {
        this.productOptionItemId = productOptionItem.id();
        this.name = productOptionItem.name();
    }

    public String name() {
        return name;
    }
}
