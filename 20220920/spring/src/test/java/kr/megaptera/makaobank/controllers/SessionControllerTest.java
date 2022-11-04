package kr.megaptera.makaobank.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import kr.megaptera.makaobank.exceptions.LoginFailed;
import kr.megaptera.makaobank.models.Account;
import kr.megaptera.makaobank.models.AccountNumber;
import kr.megaptera.makaobank.services.LoginService;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;

@WebMvcTest(SessionController.class)
@ActiveProfiles("test")
class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        AccountNumber accountNumber = new AccountNumber("1234");

        given(loginService.login(accountNumber, "password"))
                .willReturn(Account.fake(accountNumber));

        given(loginService.login(accountNumber, "xxx"))
                .willThrow(new LoginFailed());
    }

    @Test
    void loginSuccess() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/session")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{" +
                                        "\"accountNumber\":\"1234\"," +
                                        "\"password\":\"password\"" +
                                        "}")
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(
                        containsString("\"amount\":")
                ));
    }

    @Test
    void loginFail() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/session")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{" +
                                        "\"accountNumber\":\"1234\"," +
                                        "\"password\":\"xxx\"" +
                                        "}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
