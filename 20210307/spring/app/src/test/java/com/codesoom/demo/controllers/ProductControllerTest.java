// REST API
// 1. GET /products
// 2. GET /products/{id}
// 3. POST /products
// 4. PUT/PATCH /products/{id}
// 5. DELETE /products/{id}

package com.codesoom.demo.controllers;

import com.codesoom.demo.application.AuthenticationService;
import com.codesoom.demo.application.ProductService;
import com.codesoom.demo.domain.Product;
import com.codesoom.demo.domain.Role;
import com.codesoom.demo.dto.ProductData;
import com.codesoom.demo.errors.InvalidTokenException;
import com.codesoom.demo.errors.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureRestDocs
class ProductControllerTest {
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";
    private static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaD0";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        Product product = Product.builder()
                .id(1L)
                .name("쥐돌이")
                .maker("냥이월드")
                .price(5000)
                .build();

        given(productService.getProducts()).willReturn(List.of(product));

        given(productService.getProduct(1L)).willReturn(product);

        given(productService.getProduct(1000L))
                .willThrow(new ProductNotFoundException(1000L));

        given(productService.createProduct(any(ProductData.class)))
                .willReturn(product);

        given(productService.updateProduct(eq(1L), any(ProductData.class)))
                .will(invocation -> {
                    Long id = invocation.getArgument(0);
                    ProductData productData = invocation.getArgument(1);
                    return Product.builder()
                            .id(id)
                            .name(productData.getName())
                            .maker(productData.getMaker())
                            .price(productData.getPrice())
                            .build();
                });

        given(productService.updateProduct(eq(1000L), any(ProductData.class)))
                .willThrow(new ProductNotFoundException(1000L));

        given(productService.deleteProduct(1000L))
                .willThrow(new ProductNotFoundException(1000L));

        given(authenticationService.parseToken(VALID_TOKEN)).willReturn(1L);

        given(authenticationService.parseToken(INVALID_TOKEN))
                .willThrow(new InvalidTokenException(INVALID_TOKEN));

        given(authenticationService.roles(1L))
                .willReturn(Arrays.asList(new Role("USER")));
    }

    @Test
    void list() throws Exception {
        mockMvc.perform(
                get("/products")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("쥐돌이")))
                .andDo(document("get-products"));
    }

    @Test
    void deatilWithExsitedProduct() throws Exception {
        mockMvc.perform(
                get("/products/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("쥐돌이")));
    }

    @Test
    void deatilWithNotExsitedProduct() throws Exception {
        mockMvc.perform(get("/products/1000"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createWithValidAttributes() throws Exception {
        mockMvc.perform(
                post("/products")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"쥐돌이\",\"maker\":\"냥이월드\"," +
                                "\"price\":5000}")
                        .header("Authorization", "Bearer " + VALID_TOKEN)
        )
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("쥐돌이")));

        verify(productService).createProduct(any(ProductData.class));
    }

    @Test
    void createWithInvalidAttributes() throws Exception {
        mockMvc.perform(
                post("/products")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"maker\":\"\"," +
                                "\"price\":0}")
                        .header("Authorization", "Bearer " + VALID_TOKEN)
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWithoutAccessToken() throws Exception {
        mockMvc.perform(
                post("/products")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"쥐돌이\",\"maker\":\"냥이월드\"," +
                                "\"price\":5000}")
        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    void createWithWrongAccessToken() throws Exception {
        mockMvc.perform(
                post("/products")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"쥐돌이\",\"maker\":\"냥이월드\"," +
                                "\"price\":5000}")
                        .header("Authorization", "Bearer " + INVALID_TOKEN)
        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updateWithExistedProduct() throws Exception {
        mockMvc.perform(
                patch("/products/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"쥐순이\",\"maker\":\"냥이월드\"," +
                                "\"price\":5000}")
                        .header("Authorization", "Bearer " + VALID_TOKEN)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("쥐순이")));

        verify(productService).updateProduct(eq(1L), any(ProductData.class));
    }

    @Test
    void updateWithNotExistedProduct() throws Exception {
        mockMvc.perform(
                patch("/products/1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"쥐순이\",\"maker\":\"냥이월드\"," +
                                "\"price\":5000}")
                        .header("Authorization", "Bearer " + VALID_TOKEN)
        )
                .andExpect(status().isNotFound());

        verify(productService).updateProduct(eq(1000L), any(ProductData.class));
    }

    @Test
    void updateWithInvalidAttributes() throws Exception {
        mockMvc.perform(
                patch("/products/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"maker\":\"\"," +
                                "\"price\":0}")
                        .header("Authorization", "Bearer " + VALID_TOKEN)
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateWithoutAccessToken() throws Exception {
        mockMvc.perform(
                patch("/products/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"쥐순이\",\"maker\":\"냥이월드\"," +
                                "\"price\":5000}")
        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updateWithInvalidAccessToken() throws Exception {
        mockMvc.perform(
                patch("/products/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"쥐순이\",\"maker\":\"냥이월드\"," +
                                "\"price\":5000}")
                        .header("Authorization", "Bearer " + INVALID_TOKEN)
        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    void destroyWithExistedProduct() throws Exception {
        mockMvc.perform(
                delete("/products/1")
                        .header("Authorization", "Bearer " + VALID_TOKEN)
        )
                .andExpect(status().isOk());

        verify(productService).deleteProduct(1L);
    }

    @Test
    void destroyWithNotExistedProduct() throws Exception {
        mockMvc.perform(
                delete("/products/1000")
                        .header("Authorization", "Bearer " + VALID_TOKEN)
        )
                .andExpect(status().isNotFound());

        verify(productService).deleteProduct(1000L);
    }

    @Test
    void destroyWithoutAccessToken() throws Exception {
        mockMvc.perform(
                patch("/products/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"쥐순이\",\"maker\":\"냥이월드\"," +
                                "\"price\":5000}")
        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    void destroyWithInvalidAccessToken() throws Exception {
        mockMvc.perform(
                patch("/products/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"쥐순이\",\"maker\":\"냥이월드\"," +
                                "\"price\":5000}")
                        .header("Authorization", "Bearer " + INVALID_TOKEN)
        )
                .andExpect(status().isUnauthorized());
    }
}
