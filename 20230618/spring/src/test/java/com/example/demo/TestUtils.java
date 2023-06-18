package com.example.demo;

import com.example.demo.models.CartLineItemOption;
import com.example.demo.models.Product;

public class TestUtils {
    public static CartLineItemOption createCartLineItemOption(
            Product product, int optionIndex, int optionItemIndex) {
        return new CartLineItemOption(
                product.option(optionIndex).id(),
                product.option(optionIndex).item(optionItemIndex).id());
    }
}
