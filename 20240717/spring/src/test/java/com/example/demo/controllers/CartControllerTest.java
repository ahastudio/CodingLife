package com.example.demo.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.models.Cart;
import com.example.demo.repositories.CartRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(CartController.class)
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private CartRepository cartRepository;

    @Test
    @DisplayName("GET /cart")
    void getCart() throws Exception {
        mockMvc.perform(get("/cart"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"lineItems\":[]")));

        Cart cart = cartRepository.find();
        cart.addProduct(1L, 2);

        mockMvc.perform(get("/cart"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"lineItems\":[{")))
                .andExpect(content().string(containsString("\"quantity\":2")));
    }
}
