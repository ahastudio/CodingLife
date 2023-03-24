package com.example.demo.application.cart;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.Fixtures;
import com.example.demo.models.Cart;
import com.example.demo.models.LineItem;
import com.example.demo.models.LineItemId;
import com.example.demo.repositories.CartRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ChangeCartItemQuantityServiceTest {
    private CartRepository cartRepository;

    private ChangeCartItemQuantityService changeCartItemQuantityService;

    @BeforeEach
    void setUp() {
        cartRepository = mock(CartRepository.class);

        changeCartItemQuantityService =
                new ChangeCartItemQuantityService(cartRepository);
    }

    @Test
    @DisplayName("changeQuantity - when line item exists")
    void changeQuantity() {
        Cart cart = Fixtures.cart(List.of(Fixtures.product()));

        LineItem lineItem = cart.lineItem(0);
        LineItemId lineItemId = lineItem.id();

        given(cartRepository.findById(any())).willReturn(Optional.of(cart));

        changeCartItemQuantityService.changeQuantity(lineItemId, 10);

        assertThat(lineItem.quantity()).isEqualTo(10);
    }

    @Test
    @DisplayName("changeQuantity - with incorrect item ID")
    void changeQuantityWithIncorrectID() {
        Cart cart = Fixtures.cart(List.of(Fixtures.product()));

        LineItemId lineItemId = new LineItemId("test-id");

        given(cartRepository.findById(any())).willReturn(Optional.of(cart));

        assertThatThrownBy(() -> {
            changeCartItemQuantityService.changeQuantity(lineItemId, 10);
        });
    }
}
