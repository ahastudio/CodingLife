package com.ahastudio.user.controllers;

import javax.persistence.EntityNotFoundException;

import com.ahastudio.user.application.AuthenticationService;
import com.ahastudio.user.models.User;
import com.ahastudio.user.respositories.UserRepository;
import com.ahastudio.user.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private AuthenticationService authenticationService;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private PasswordEncoder passwordEncoder;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void loginWithRightEmailAndPassword() throws Exception {
        User user = new User();
        user.changePassword(passwordEncoder, "test");

        given(userRepository.getByEmail("tester@example.com")).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"email\":\"tester@example.com\"," +
                                "\"password\":\"test\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString(".")
                ));
    }

    @Test
    void loginWithWrongEmail() throws Exception {
        given(userRepository.getByEmail(any()))
                .willThrow(new EntityNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"email\":\"not.found@example.com\"," +
                                "\"password\":\"test\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void loginWithWrongPassword() throws Exception {
        User user = new User();
        user.changePassword(passwordEncoder, "test");

        given(userRepository.getByEmail("tester@example.com")).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"email\":\"tester@example.com\"," +
                                "\"password\":\"wrong\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }
}
