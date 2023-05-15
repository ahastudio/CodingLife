package com.example.demo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.application.LoginService;
import com.example.demo.application.LogoutService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
class SessionControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private LogoutService logoutService;

    @BeforeEach
    void setUp() {
        given(loginService.login("tester@example.com", "password"))
                .willReturn("Tester.Access.Token");

        given(loginService.login("xxx", "password"))
                .willThrow(new BadCredentialsException("Login failed"));

        given(loginService.login("tester@example.com", "xxx"))
                .willThrow(new BadCredentialsException("Login failed"));
    }

    @Test
    @DisplayName("POST /session - with correct email and password")
    void loginSuccess() throws Exception {
        String json = """
                {
                    "email": "tester@example.com",
                    "password": "password"
                }
                """;

        mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /session - with incorrect email")
    void loginWithIncorrectUsername() throws Exception {
        String json = """
                {
                    "email": "xxx",
                    "password": "password"
                }
                """;

        mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /session - with incorrect password")
    void loginWithIncorrectPassword() throws Exception {
        String json = """
                {
                    "email": "tester@example.com",
                    "password": "xxx"
                }
                """;

        mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("DELETE /session - with correct access token")
    void logoutWithCorrectAccessToken() throws Exception {
        mockMvc.perform(delete("/session")
                        .header("Authorization", "Bearer " + userAccessToken))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /session - with incorrect access token")
    void logoutWithIncorrectAccessToken() throws Exception {
        mockMvc.perform(delete("/session")
                        .header("Authorization", "Bearer XXX"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("DELETE /session - without access token")
    void logoutWithoutAccessToken() throws Exception {
        mockMvc.perform(delete("/session"))
                .andExpect(status().isForbidden());
    }
}
