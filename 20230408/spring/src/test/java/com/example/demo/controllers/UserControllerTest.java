package com.example.demo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.SignupService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private SignupService signupService;

    @SpyBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        given(userDetailsDao.existsByUsername("tester")).willReturn(true);
    }

    @Test
    @DisplayName("POST /users - with valid attributes")
    void signupSuccess() throws Exception {
        String json = """
                {
                    "username": "newbie",
                    "password": "password"
                }
                """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /users - with invalid attributes")
    void signupWithInvalidAttributes() throws Exception {
        String json = """
                {
                    "username": "",
                    "password": ""
                }
                """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /users - when username already exists")
    void signupDuplicated() throws Exception {
        String json = """
                {
                    "username": "tester",
                    "password": "password"
                }
                """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
