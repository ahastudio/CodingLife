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

import com.ahastudio.demo.shop.models.Shop;
import com.ahastudio.demo.shop.models.ShopId;
import com.ahastudio.demo.shop.repositories.ShopRepository;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShopController.class)
@ComponentScan("com.ahastudio.demo.shop.config")
@ActiveProfiles("test")
class ShopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopRepository shopRepository;

    @Test
    @DisplayName("GET /shops")
    void list() throws Exception {

        Shop shop = Shop.builder()
                .id(new ShopId("00010001"))
                .name("Test")
                .build();

        given(shopRepository.findAll()).willReturn(List.of(shop));

        mockMvc.perform(get("/shops"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"shops\":[")
                ))
                .andExpect(content().string(
                        containsString("\"id\":\"00010001\"")
                ));
    }

    @Test
    @DisplayName("GET /shops/{id} (when shop exists)")
    void detail() throws Exception {
        ShopId id = new ShopId("00010001");

        Shop shop = Shop.builder()
                .id(id)
                .name("Test")
                .build();

        given(shopRepository.findById(id)).willReturn(Optional.of(shop));

        mockMvc.perform(get("/shops/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":\"" + id + "\"")
                ));
    }

    @Test
    @DisplayName("GET /shops/{id} (when shop doesn't exist)")
    void detailNotFound() throws Exception {
        given(shopRepository.findById(any())).willReturn(Optional.empty());

        mockMvc.perform(get("/shops/404"))
                .andExpect(status().isNotFound());
    }
}
