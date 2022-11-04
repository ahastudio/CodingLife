package kr.megaptera.makaobank.controllers;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import kr.megaptera.makaobank.exceptions.AccountNotFound;
import kr.megaptera.makaobank.models.Account;
import kr.megaptera.makaobank.models.AccountNumber;
import kr.megaptera.makaobank.services.AccountService;
import kr.megaptera.makaobank.utils.JwtUtil;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@ActiveProfiles("test")
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void account() throws Exception {
        String token = jwtUtil.encode(new AccountNumber("1234"));

        given(accountService.detail(any())).willReturn(Account.fake("1234"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/accounts/me")
                                .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"accountNumber\":\"1234\"")
                ));
    }

    @Test
    void accountNoutFound() throws Exception {
        String token = jwtUtil.encode(new AccountNumber("1234"));

        given(accountService.detail(any()))
                .willThrow(new AccountNotFound(new AccountNumber("1234")));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/accounts/me")
                                .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void accountWithoutAccessToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/me"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void accountWithIncorrectAccessToken() throws Exception {
        String token = "xxx";

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/accounts/me")
                                .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isBadRequest());
    }
}
