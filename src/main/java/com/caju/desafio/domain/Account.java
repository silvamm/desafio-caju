package com.caju.desafio.domain;

import java.math.BigDecimal;

public class Account {
    private Long id;
    private BigDecimal foodBalance;
    private BigDecimal mealBalance;
    private BigDecimal cashBalance;

    public Long getId() {
        return id;
    }

    public BigDecimal getFoodBalance() {
        return foodBalance;
    }

    public BigDecimal getMealBalance() {
        return mealBalance;
    }

    public BigDecimal getCashBalance() {
        return cashBalance;
    }
}
