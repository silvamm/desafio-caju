package com.caju.desafio.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MccTypeTest {

    @Test
    public void shouldReturnFoodTypeByMcc(){
        assertEquals(MccType.FOOD, MccType.findByCode("5411"));
        assertEquals(MccType.FOOD, MccType.findByCode("5412"));
    }

    @Test
    public void shouldReturnMealTypeByMcc(){
        assertEquals(MccType.MEAL, MccType.findByCode("5811"));
        assertEquals(MccType.MEAL, MccType.findByCode("5812"));
    }

    @Test
    public void shouldReturnCashTypeByMccForAnythingElse(){
        assertEquals(MccType.CASH, MccType.findByCode("any"));
    }
}
