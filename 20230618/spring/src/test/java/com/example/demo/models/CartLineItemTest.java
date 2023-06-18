package com.example.demo.models;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.example.demo.Fixtures;

import static com.example.demo.TestUtils.createCartLineItemOption;
import static org.assertj.core.api.Assertions.assertThat;

class CartLineItemTest {
    @Test
    void sameProduct() {
        Product product = Fixtures.product("맨투맨");

        ProductId productId = product.id();

        Set<CartLineItemOption> options1 = Set.of(
                createCartLineItemOption(product, 0, 0),
                createCartLineItemOption(product, 1, 0)
        );

        Set<CartLineItemOption> options2 = Set.of(
                createCartLineItemOption(product, 0, 1),
                createCartLineItemOption(product, 1, 1)
        );

        CartLineItem lineItem = new CartLineItem(
                CartLineItemId.generate(), productId, options1, 1);

        assertThat(lineItem.sameProduct(productId, options1)).isTrue();

        assertThat(lineItem.sameProduct(productId, options2)).isFalse();
    }

    @Test
    void optionItemId() {
        Product product = Fixtures.product("맨투맨");

        ProductId productId = product.id();

        Set<CartLineItemOption> options = Set.of(
                createCartLineItemOption(product, 0, 0),
                createCartLineItemOption(product, 1, 0)
        );

        List<ProductOptionId> optionIds = options.stream()
                .map(CartLineItemOption::optionId)
                .toList();

        List<ProductOptionItemId> optionItemIds = options.stream()
                .map(CartLineItemOption::optionItemId)
                .toList();

        CartLineItem lineItem = new CartLineItem(
                CartLineItemId.generate(), productId, options, 1);

        assertThat(lineItem.optionIds()).hasSameElementsAs(optionIds);

        IntStream.range(0, 2).forEach(index -> {
            assertThat(lineItem.optionItemId(optionIds.get(index)))
                    .isEqualTo(optionItemIds.get(index));
        });
    }
}
