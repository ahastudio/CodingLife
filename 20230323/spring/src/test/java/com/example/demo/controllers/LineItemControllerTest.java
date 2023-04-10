package com.example.demo.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.cart.AddProductToCartService;
import com.example.demo.application.cart.ChangeCartItemQuantityService;
import com.example.demo.application.cart.GetCartService;
import com.example.demo.controllers.dtos.CartDto;
import com.example.demo.controllers.helpers.ControllerTest;
import com.example.demo.models.LineItemId;
import com.example.demo.models.ProductId;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LineItemController.class)
class LineItemControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetCartService getCartService;

    @MockBean
    private AddProductToCartService addProductToCartService;

    @MockBean
    private ChangeCartItemQuantityService changeCartItemQuantityService;

    @Test
    @DisplayName("GET /cart-line-items")
    void list() throws Exception {
        given(getCartService.getCartDto()).willReturn(new CartDto(List.of()));

        mockMvc.perform(get("/cart-line-items")
                        .header("Authorization", "Bearer " + userAccessToken)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /cart-line-items")
    void create() throws Exception {
        ProductId productId = new ProductId("test-id");

        String json = String.format(
                """
                        {
                            "productId": "%s",
                            "quantity": 3
                        }
                        """,
                productId
        );

        mockMvc.perform(post("/cart-line-items")
                        .header("Authorization", "Bearer " + userAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(addProductToCartService).addProduct(productId, 3);
    }

    @Test
    @DisplayName("PATCH /cart-line-items/{id} - with correct ID")
    void update() throws Exception {
        LineItemId lineItemId = new LineItemId("test-id");

        String json = "{\"quantity\": 3}";

        mockMvc.perform(patch("/cart-line-items/" + lineItemId)
                        .header("Authorization", "Bearer " + userAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());

        verify(changeCartItemQuantityService).changeQuantity(lineItemId, 3);
    }

    @Test
    @DisplayName("PATCH /cart-line-items/{id} - with incorrect ID")
    void updateWithIncorrectID() throws Exception {
        LineItemId lineItemId = new LineItemId("BAD");

        String json = "{\"quantity\": 3}";

        doThrow(new NoSuchElementException())
                .when(changeCartItemQuantityService)
                .changeQuantity(lineItemId, 3);

        mockMvc.perform(patch("/cart-line-items/" + lineItemId)
                        .header("Authorization", "Bearer " + userAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }
}
