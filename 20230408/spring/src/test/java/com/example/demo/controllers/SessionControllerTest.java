package com.example.demo.controllers;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @SpyBean
    private LoginService loginService;

    @SpyBean
    private LogoutService logoutService;

    @SpyBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        UserDetails userDetails = User.withUsername("UserID")
                .password(passwordEncoder.encode("password"))
                .authorities("ROLE_USER")
                .build();

        given(userDetailsDao.findByUsername("tester"))
                .willReturn(Optional.of(userDetails));
    }

    @Test
    @DisplayName("POST /session - with correct username and password")
    void loginSuccess() throws Exception {
        String json = """
                {
                    "username": "tester",
                    "password": "password"
                }
                """;

        mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /session - with incorrect username")
    void loginWithIncorrectUsername() throws Exception {
        String json = """
                {
                    "username": "xxx",
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
                    "username": "tester",
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
