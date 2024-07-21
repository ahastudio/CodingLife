package com.example.demo.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.models.Cart;
import com.example.demo.repositories.CartRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartLineItemController.class)
class CartLineItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private CartRepository cartRepository;

    @Test
    @DisplayName("POST /cart-line-items (when cart hasn't product)")
    void addNewProduct() throws Exception {
        Cart cart = cartRepository.find();

        assertThat(cart.lineItemsCount()).isEqualTo(0);

        String json = """
                {
                    "productId": 1,
                    "quantity": 2
                }
                """;

        mockMvc.perform(post("/cart-line-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated());

        assertThat(cart.lineItemsCount()).isEqualTo(1);
    }
}
