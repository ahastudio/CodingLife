package com.example.demo.controllers;

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

import com.example.demo.models.Money;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;
import com.example.demo.repositories.ProductRepository;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ComponentScan("com.example.demo.config")
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
                .id(new ProductId(201L))
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
                        containsString("\"id\":\"201\"")
                ));
    }

    @Test
    @DisplayName("GET /products/{id} (when product exists)")
    void detail() throws Exception {
        ProductId id = new ProductId(201L);

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
