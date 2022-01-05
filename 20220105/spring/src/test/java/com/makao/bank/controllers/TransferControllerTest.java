package com.makao.bank.controllers;

import com.makao.bank.models.Account;
import com.makao.bank.respositories.AccountRepository;
import com.makao.bank.services.AccountService;
import com.makao.bank.services.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransferController.class)
class TransferControllerTest {
    private static final String ACCOUNT_IDENTIFIER = "1234";
    private static final long INITIAL_AMOUNT = 3000;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private TransferService transferService;

    @SpyBean
    private AccountService accountService;

    @SpyBean
    private AccountRepository accountRepository;

    @BeforeEach
    void resetAccounts() {
        accountRepository.reset();
    }

    @Test
    void transferPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transfer"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("받는 사람 계좌:")
                ));
    }

    @Test
    void transfer1000() throws Exception {
        long amount = 1000;

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/transfer")
                                .param("to", "2345")
                                .param("amount", Long.toString(amount)))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("계좌 이체 성공!")
                ));

        Account account = accountRepository.find(ACCOUNT_IDENTIFIER);
        assertThat(account.amount()).isEqualTo(INITIAL_AMOUNT - amount);
    }

    @Test
    void transfer500() throws Exception {
        long amount = 500;

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/transfer")
                                .param("to", "2345")
                                .param("amount", Long.toString(amount)))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("계좌 이체 성공!")
                ));

        Account account = accountRepository.find(ACCOUNT_IDENTIFIER);
        assertThat(account.amount()).isEqualTo(INITIAL_AMOUNT - amount);
    }

//    @Test
//    void transferMinus() throws Exception {
//        long amount = -100;
//
//        mockMvc.perform(
//                        MockMvcRequestBuilders.post("/transfer")
//                                .param("to", "2345")
//                                .param("amount", Long.toString(amount)))
//                .andExpect(status().isOk())
//                .andExpect(content().string(
//                        containsString("이체 금액 오류")
//                ));
//    }

    @Test
    void transferWithWrongReceiver() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/transfer")
                                .param("to", "-1")
                                .param("amount", "1000"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("계좌 번호를 확인해 주세요")
                ));

        Account account = accountRepository.find(ACCOUNT_IDENTIFIER);
        assertThat(account.amount()).isEqualTo(INITIAL_AMOUNT);
    }
}
