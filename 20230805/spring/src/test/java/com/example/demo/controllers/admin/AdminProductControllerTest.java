package com.example.demo.controllers.admin;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.CreateProductService;
import com.example.demo.application.GetProductDetailService;
import com.example.demo.application.GetProductListService;
import com.example.demo.application.UpdateProductService;
import com.example.demo.controllers.ControllerTest;
import com.example.demo.dtos.CategoryDto;
import com.example.demo.dtos.ImageDto;
import com.example.demo.dtos.admin.AdminProductDetailDto;
import com.example.demo.dtos.admin.AdminProductListDto;
import com.example.demo.dtos.admin.AdminProductSummaryDto;
import com.example.demo.models.ProductId;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminProductController.class)
class AdminProductControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetProductListService getProductListService;

    @MockBean
    private GetProductDetailService getProductDetailService;

    @MockBean
    private CreateProductService createProductService;

    @MockBean
    private UpdateProductService updateProductService;

    @Test
    @DisplayName("GET /admin/products")
    void list() throws Exception {
        AdminProductSummaryDto productDto = new AdminProductSummaryDto(
                "PRODUCT-ID",
                new CategoryDto("CATEGORY-ID", "Category"),
                new ImageDto("http://example.com/01.jpg"),
                "Product",
                100_000L,
                false
        );

        given(getProductListService.getAdminProductListDto())
                .willReturn(new AdminProductListDto(List.of(productDto)));

        mockMvc.perform(get("/admin/products")
                        .header("Authorization", "Bearer " + adminAccessToken))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /admin/products/{id}")
    void detail() throws Exception {
        ProductId productId = new ProductId("1234");

        AdminProductDetailDto productDto = new AdminProductDetailDto(
                productId.toString(),
                new CategoryDto("CATEGORY-ID", "Category"),
                List.of(new ImageDto("http://example.com/01.jpg")),
                "Product",
                100_000L,
                List.of(),
                "Description",
                false
        );

        given(getProductDetailService.getAdminProductDetailDto(productId))
                .willReturn(productDto);

        mockMvc.perform(get("/admin/products/" + productId)
                        .header("Authorization", "Bearer " + adminAccessToken))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /admin/products")
    void create() throws Exception {
        String json = """
                {
                    "categoryId": "0BV000CAT0001",
                    "images": [
                        {
                            "url": "https://example.com/products/01.jpg"
                        }
                    ],
                    "name": "맨투맨",
                    "price": 128000,
                    "options": [
                        {
                            "name": "컬러",
                            "items": [
                                {
                                    "name": "black"
                                },
                                {
                                    "name": "white"
                                }
                            ]
                        },
                        {
                            "name": "사이즈",
                            "items": [
                                {
                                    "name": "S"
                                },
                                {
                                    "name": "M"
                                }
                            ]
                        }
                    ],
                    "description": "편하게 입을 수 있는 맨투맨"
                }
                """;

        mockMvc.perform(post("/admin/products")
                        .header("Authorization", "Bearer " + adminAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(createProductService).createProduct(
                any(), any(), any(), any(), any(), any());
    }

    @Test
    @DisplayName("PATCH /admin/products/{id}")
    void update() throws Exception {
        String json = """
                {
                    "categoryId": "0BV000CAT0001",
                    "images": [
                        {
                            "id": "0BV000IMG0001",
                            "url": "https://example.com/products/01.jpg"
                        },
                        {
                            "url": "https://example.com/products/02.jpg"
                        }
                    ],
                    "name": "맨투맨",
                    "price": 128000,
                    "options": [
                        {
                            "id": "0BV000OPT0001",
                            "name": "컬러",
                            "items": [
                                {
                                    "id": "0BV000ITEM001",
                                    "name": "black"
                                },
                                {
                                    "name": "white"
                                }
                            ]
                        },
                        {
                            "name": "사이즈",
                            "items": [
                                {
                                    "name": "S"
                                },
                                {
                                    "name": "M"
                                }
                            ]
                        }
                    ],
                    "description": "편하게 입을 수 있는 맨투맨",
                    "hidden": false
                }
                """;

        mockMvc.perform(patch("/admin/products/123")
                        .header("Authorization", "Bearer " + adminAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(updateProductService)
                .updateProduct(eq(new ProductId("123")), any());
    }
}
