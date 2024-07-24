package com.caju.desafio.dataprovider.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
