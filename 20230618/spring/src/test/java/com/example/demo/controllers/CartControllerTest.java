package com.example.demo.controllers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.GetCartService;
import com.example.demo.dtos.CartDto;
import com.example.demo.models.UserId;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
class CartControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetCartService getCartService;

    @Test
    @DisplayName("GET /cart")
    void detail() throws Exception {
        CartDto cartDto = new CartDto(List.of(), 0L);

        given(getCartService.getCartDto(new UserId(USER_ID)))
                .willReturn(cartDto);

        mockMvc.perform(get("/cart")
                        .header("Authorization", "Bearer " + userAccessToken))
                .andExpect(status().isOk());
    }
}
