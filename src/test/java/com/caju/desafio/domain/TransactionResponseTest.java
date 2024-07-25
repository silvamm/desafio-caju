package com.caju.desafio.domain;

import org.junit.jupiter.api.Test;

import static com.caju.desafio.domain.TransactionStatus.APPROVED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionResponseTest {

    @Test
    public void shouldHaveCodeAsSameValueTransactionStatus(){
        assertEquals(APPROVED.getCode(), new TransactionResponse(APPROVED).getCode());
    }
}
