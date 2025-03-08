package com.example.demo.controllers;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.GetUserListService;
import com.example.demo.domain.User;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetUserListService getUserListService;

    @Test
    @DisplayName("GET /users")
    void list() throws Exception {
        given(getUserListService.getUsers()).willReturn(List.of(
            new User(1L, "소", "크라테스", "test@example.com")));

        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            // 정확히 일치하는 걸 확인하면 깨지기 쉬움.
//            .andExpect(content().string(
//                "{\"users\":[{\"id\":1,\"name\":\"소크라테스\"}]"
//            ));
            .andExpect(content().string(
                containsString("{\"users\":[{")
            ))
            .andExpect(content().string(
                containsString("\"name\":\"소크라테스\"")
            ));
    }
}
