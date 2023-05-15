package com.example.demo.controllers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.GetProductDetailService;
import com.example.demo.application.GetProductListService;
import com.example.demo.dtos.CategoryDto;
import com.example.demo.dtos.ImageDto;
import com.example.demo.dtos.ProductDetailDto;
import com.example.demo.dtos.ProductListDto;
import com.example.demo.dtos.ProductSummaryDto;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetProductListService getProductListService;

    @MockBean
    private GetProductDetailService getProductDetailService;

    @Test
    @DisplayName("GET /products")
    void list() throws Exception {
        ProductSummaryDto productDto = new ProductSummaryDto(
                "PRODUCT-ID",
                new CategoryDto("CATEGORY-ID", "Category"),
                new ImageDto("http://example.com/01.jpg"),
                "Product",
                100_000L
        );

        given(getProductListService.getProductListDto(null))
                .willReturn(new ProductListDto(List.of(productDto)));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product")));
    }

    @Test
    @DisplayName("GET /products/{id}")
    void detail() throws Exception {
        ProductDetailDto productDto = new ProductDetailDto(
                "PRODUCT-ID",
                new CategoryDto("CATEGORY-ID", "Category"),
                List.of(new ImageDto("http://example.com/01.jpg")),
                "Product",
                100_000L,
                List.of(),
                ""
        );

        given(getProductDetailService.getProductDetailDto("1234"))
                .willReturn(productDto);

        mockMvc.perform(get("/products/1234"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product")));
    }
}
