package kr.megaptera.makaobank.controllers;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import kr.megaptera.makaobank.exceptions.AccountNotFound;
import kr.megaptera.makaobank.exceptions.IncorrectAmount;
import kr.megaptera.makaobank.models.AccountNumber;
import kr.megaptera.makaobank.models.Transaction;
import kr.megaptera.makaobank.services.TransactionService;
import kr.megaptera.makaobank.services.TransferService;
import kr.megaptera.makaobank.utils.JwtUtil;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@WebMvcTest(TransactionController.class)
@ActiveProfiles("test")
class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private TransferService transferService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void list() throws Exception {
        String token = jwtUtil.encode(new AccountNumber("1234"));

        AccountNumber accountNumber = new AccountNumber("1234");

        Transaction transaction = mock(Transaction.class);

        given(transactionService.list(accountNumber, 1))
                .willReturn(List.of(transaction));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/transactions")
                                .header("Authorization", "Bearer " + token)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        containsString("\"transactions\":[")
                ));

        verify(transactionService).list(accountNumber, 1);
    }

    @Test
    void transfer() throws Exception {
        String token = jwtUtil.encode(new AccountNumber("1234"));

        AccountNumber sender = new AccountNumber("1234");
        AccountNumber receiver = new AccountNumber("5678");
        String name = "Test";

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/transactions")
                                .header("Authorization", "Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{" +
                                        "\"to\":\"" + receiver.value() + "\"," +
                                        "\"amount\":100000," +
                                        "\"name\":\"" + name + "\"" +
                                        "}")
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(transferService).transfer(sender, receiver, 100_000L, name);
    }

    @Test
    void transferWithIncorrectAccountNumber() throws Exception {
        String token = jwtUtil.encode(new AccountNumber("1234"));

        AccountNumber accountNumber = new AccountNumber("1234");

        given(transferService.transfer(any(), any(), any(), any()))
                .willThrow(new AccountNotFound(accountNumber));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/transactions")
                                .header("Authorization", "Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{" +
                                        "\"to\":\"" + accountNumber.value() + "\"," +
                                        "\"amount\":100000," +
                                        "\"name\":\"Test\"" +
                                        "}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(
                        containsString("\"code\":1001")
                ));
    }

    @Test
    void transferWithIncorrectAmount() throws Exception {
        String token = jwtUtil.encode(new AccountNumber("1234"));

        Long amount = 100_000L;

        given(transferService.transfer(any(), any(), any(), any()))
                .willThrow(new IncorrectAmount(amount));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/transactions")
                                .header("Authorization", "Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{" +
                                        "\"to\":\"5678\"," +
                                        "\"amount\":" + amount + "," +
                                        "\"name\":\"Test\"" +
                                        "}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(
                        containsString("\"code\":1002")
                ));
    }
}
