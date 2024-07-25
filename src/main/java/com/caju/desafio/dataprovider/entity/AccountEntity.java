package com.caju.desafio.dataprovider.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal foodBalance;
    @Column(nullable = false)
    private BigDecimal mealBalance;
    @Column(nullable = false)
    private BigDecimal cashBalance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getFoodBalance() {
        return foodBalance;
    }

    public void setFoodBalance(BigDecimal foodBalance) {
        this.foodBalance = foodBalance;
    }

    public BigDecimal getMealBalance() {
        return mealBalance;
    }

    public void setMealBalance(BigDecimal mealBalance) {
        this.mealBalance = mealBalance;
    }

    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(BigDecimal cashBalance) {
        this.cashBalance = cashBalance;
    }
}
