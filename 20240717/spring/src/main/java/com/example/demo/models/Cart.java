package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<LineItem> lineItems = new ArrayList<>();

    public void addProduct(Long productId, int quantity) {
        if (quantity <= 0) {
            return;
        }

        Optional<LineItem> found = lineItems.stream()
                .filter(lineItem -> lineItem.sameProduct(productId))
                .findFirst();

        if (found.isPresent()) {
            LineItem lineItem = found.orElseThrow();
            lineItem.increaseQuantity(quantity);
            return;
        }

        LineItem lineItem = new LineItem(productId, quantity);
        lineItems.add(lineItem);
    }

    public int lineItemsCount() {
        return lineItems.size();
    }

    public LineItem lineItem(int index) {
        return lineItems.get(index);
    }
}
