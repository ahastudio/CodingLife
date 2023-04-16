package com.example.demo.controllers;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import org.aspectj.util.FileUtil;

import com.example.demo.application.product.CreateProductService;
import com.example.demo.application.product.GetProductListService;
import com.example.demo.controllers.dtos.ProductListDto;
import com.example.demo.controllers.helpers.ControllerTest;
import com.example.demo.models.Money;
import com.example.demo.utils.ImageStorage;

import static com.example.demo.controllers.helpers.ResultMatchers.contentContains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageStorage imageStorage;

    @MockBean
    private GetProductListService getProductListService;

    @MockBean
    private CreateProductService createProductService;

    @Test
    @DisplayName("GET /products")
    void list() throws Exception {
        ProductListDto.ProductDto productDto =
                new ProductListDto.ProductDto("test-id", "제품", 100_000L, null);

        given(getProductListService.getProductListDto()).willReturn(
                new ProductListDto(List.of(productDto)));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(contentContains("제품"));
    }

    @Test
    @DisplayName("POST /products")
    void create() throws Exception {
        String filename = "src/test/resources/files/test.jpg";

        byte[] content = FileUtil.readAsByteArray(new File(filename));

        MockMultipartFile file = new MockMultipartFile(
                "image", "test.jpg", "image/jpeg", content);

        mockMvc.perform(multipart("/products")
                        .file(file)
                        .param("name", "멋진 제품")
                        .param("price", "100000")
                        .header("Authorization", "Bearer " + adminAccessToken))
                .andExpect(status().isCreated());

        verify(createProductService)
                .createProduct("멋진 제품", new Money(100_000L), content);
    }
}
