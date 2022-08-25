package kr.megaptera.makaobank.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void account() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/me"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
