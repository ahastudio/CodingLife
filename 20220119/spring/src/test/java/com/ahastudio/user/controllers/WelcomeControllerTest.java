package com.ahastudio.user.controllers;

import java.util.Optional;

import com.ahastudio.user.interceptors.AuthenticationInterceptor;
import com.ahastudio.user.models.User;
import com.ahastudio.user.respositories.UserRepository;
import com.ahastudio.user.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WelcomeController.class)
class WelcomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private AuthenticationInterceptor authenticationInterceptor;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void home() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("Hello, world!")
                ));
    }

    @Test
    void homeWithAccessToken() throws Exception {
        User user = new User(1004L, "Tester", "tester@example.com");
        given(userRepository.findById(1004L)).willReturn(Optional.of(user));

        String accessToken = jwtUtil.encode(1004L);

        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("Hello, Tester!")
                ));
    }


    @Test
    void homeWithWrongAuthorationHeader() throws Exception {
        User user = new User(1004L, "Tester", "tester@example.com");
        given(userRepository.findById(1004L)).willReturn(Optional.of(user));

        String accessToken = jwtUtil.encode(1004L);

        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .header("Authorization", "Wrong " + accessToken))
                .andExpect(status().isBadRequest());
    }
}
