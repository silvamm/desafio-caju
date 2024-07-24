package com.caju.desafio.domain;

import java.math.BigDecimal;

public class Account {
    private final Long id;
    private BigDecimal foodBalance;
    private BigDecimal mealBalance;
    private BigDecimal cashBalance;

    public Account(Long id, BigDecimal foodBalance, BigDecimal mealBalance, BigDecimal cashBalance) {
        this.id = id;
        this.foodBalance = foodBalance;
        this.mealBalance = mealBalance;
        this.cashBalance = cashBalance;
    }

    public TransactionStatus execute(Transaction transaction) {
        if (!this.id.equals(transaction.getAccountId()))
            return TransactionStatus.TRANSACTION_PROBLEM;

        return switch (transaction.getMccType()){
            case FOOD -> reduceFoodBalance(transaction.getAmount());
            case MEAL -> reduceMealBalance(transaction.getAmount());
            case CASH -> reduceCashBalance(transaction.getAmount());
        };
    }

    private TransactionStatus reduceFoodBalance(BigDecimal ammount){
        if(foodBalance.compareTo(ammount) < 0)
             return reduceCashBalance(ammount);

        foodBalance = foodBalance.subtract(ammount);
        return TransactionStatus.APPROVED;
    }

    private TransactionStatus reduceMealBalance(BigDecimal ammount){
        if(mealBalance.compareTo(ammount) < 0)
            return reduceCashBalance(ammount);

        mealBalance = mealBalance.subtract(ammount);
        return TransactionStatus.APPROVED;
    }

    private TransactionStatus reduceCashBalance(BigDecimal ammount){
        if(cashBalance.compareTo(ammount) < 0)
            return TransactionStatus.REJECTED;

        cashBalance = cashBalance.subtract(ammount);
        return TransactionStatus.APPROVED;
    }

    public Long getId() {
        return this.id;
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
