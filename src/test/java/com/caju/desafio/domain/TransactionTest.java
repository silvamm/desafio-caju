package com.caju.desafio.domain;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    @Test
    public void shouldHaveAnUuidAsId(){
        var transaction = new Transaction(1L, BigDecimal.ONE, "UBER", "any");

        assertEquals(UUID.fromString(transaction.getId()).toString(), transaction.getId());
    }

    @Test
    public void shouldOverrideTransactionMccByMerchantsIfThereIsInsideMap(){
        var transaction = new Transaction(1L, BigDecimal.ONE, "correctMerchant", "wrongMcc");

        var map = new HashMap<String, String>();
        map.put("correctMerchant", "5411");

        transaction.overrideMccByMerchants(map);

        assertEquals(map.get("correctMerchant"), transaction.getMcc());
    }

    @Test
    public void shouldNotOverrideTransactionMccByMerchantsIfThereIsNotInsideMap(){
        var transaction = new Transaction(1L, BigDecimal.ONE, "correctMerchant", "wrongMcc");

        var map = new HashMap<String, String>();
        map.put("anotherMerchant", "5411");

        transaction.overrideMccByMerchants(map);

        assertEquals("wrongMcc", transaction.getMcc());
    }
}
