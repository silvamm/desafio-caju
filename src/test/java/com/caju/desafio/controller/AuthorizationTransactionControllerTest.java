package com.caju.desafio.controller;

import com.caju.desafio.api.AuthorizationTransactionController;
import com.caju.desafio.api.dto.PutRequestTransaction;
import com.caju.desafio.domain.Transaction;
import com.caju.desafio.domain.TransactionResponse;
import com.caju.desafio.domain.TransactionService;
import com.caju.desafio.domain.TransactionStatus;
import com.caju.desafio.mapper.TransactionMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorizationTransactionController.class)
public class AuthorizationTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService authorizationTransactionService;

    @MockBean
    private TransactionMapper transactionMapper;

    @Test
    void shouldAuthorizeTransaction() throws Exception {

        PutRequestTransaction request = new PutRequestTransaction(1L, BigDecimal.ONE, "mcc", "merchant");
        Transaction transaction = new Transaction(1L, BigDecimal.TEN, "mcc", "merchant");
        TransactionResponse response = new TransactionResponse(TransactionStatus.APPROVED);

        when(transactionMapper.toDomain(request)).thenReturn(transaction);
        when(authorizationTransactionService.execute(transaction)).thenReturn(response);

        var objectMapper = new ObjectMapper();
        var payload = objectMapper.writeValueAsString(request);

        mockMvc.perform(put("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(response.getCode()));
    }
}
