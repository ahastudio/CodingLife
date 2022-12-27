package com.ahastudio.demo.shop.controllers;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.ahastudio.demo.shop.models.Money;
import com.ahastudio.demo.shop.models.Product;
import com.ahastudio.demo.shop.models.ProductId;
import com.ahastudio.demo.shop.repositories.ProductRepository;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ComponentScan("com.ahastudio.demo.shop.config")
@ActiveProfiles("test")
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    @DisplayName("GET /products")
    void list() throws Exception {
        Product product = Product.builder()
                .id(new ProductId("00020001"))
                .name("Test")
                .price(Money.krw(10_000L))
                .build();

        given(productRepository.findAll()).willReturn(List.of(product));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"products\":[")
                ))
                .andExpect(content().string(
                        containsString("\"id\":\"00020001\"")
                ));
    }

    @Test
    @DisplayName("GET /products/{id} (when product exists)")
    void detail() throws Exception {
        ProductId id = new ProductId("00020001");

        Product product = Product.builder()
                .id(id)
                .name("Test")
                .price(Money.krw(10_000L))
                .build();

        given(productRepository.findById(id)).willReturn(Optional.of(product));

        mockMvc.perform(get("/products/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":\"" + id + "\"")
                ));
    }

    @Test
    @DisplayName("GET /products/{id} (when product doesn't exist)")
    void detailNotFound() throws Exception {
        given(productRepository.findById(any())).willReturn(Optional.empty());

        mockMvc.perform(get("/products/404"))
                .andExpect(status().isNotFound());
    }
}
