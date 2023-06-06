package com.example.demo.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.AddProductToCartService;
import com.example.demo.models.ProductId;
import com.example.demo.models.UserId;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartLineItemController.class)
class CartLineItemControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddProductToCartService addProductToCartService;

    @Test
    @DisplayName("POST /cart/line-items")
    void addToCart() throws Exception {
        UserId userId = new UserId(USER_ID);
        ProductId productId = new ProductId("0BV000PRO0001");
        int quantity = 1;

        String json = String.format(
                """
                        {
                            "productId": "%s",
                            "options": [
                                {
                                    "id": "0BV000OPT0001",
                                    "itemId": "0BV000ITEM001"
                                },
                                {
                                    "id": "0BV000OPT0002",
                                    "itemId": "0BV000ITEM004"
                                }
                            ],
                            "quantity": %d
                        }
                        """,
                productId, quantity
        );

        mockMvc.perform(post("/cart/line-items")
                        .header("Authorization", "Bearer " + userAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(addProductToCartService).addProductToCart(
                eq(userId), eq(productId), any(), eq(quantity));
    }
}
