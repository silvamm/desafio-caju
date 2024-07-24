package com.caju.desafio.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    @Test
    void shouldNotReduceAnyBalanceIfIdIsNotEqualWithTransaction(){
        var account = new Account(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        var transaction = new Transaction(100L, BigDecimal.ONE, "SAO PAULO", MccType.FOOD.getFirstCodeIfExist());

        var transactionStatus = account.execute(transaction);

        assertEquals(TransactionStatus.TRANSACTION_PROBLEM, transactionStatus);
    }
    @Test
    void shouldReduceFoodBalance(){
        var account = new Account(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        var transaction = new Transaction(account.getId(), BigDecimal.ONE, "SAO PAULO", MccType.FOOD.getFirstCodeIfExist());

        var transactionStatus = account.execute(transaction);

        assertEquals(TransactionStatus.APPROVED, transactionStatus);
        assertEquals(BigDecimal.valueOf(9), account.getFoodBalance());
    }

    @Test
    void shouldNotReduceFoodBalance(){
        var account = new Account(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        var transaction = new Transaction(account.getId(), BigDecimal.valueOf(11), "SAO PAULO", MccType.FOOD.getFirstCodeIfExist());

        var transactionStatus = account.execute(transaction);

        assertEquals(TransactionStatus.REJECTED, transactionStatus);
        assertEquals(BigDecimal.valueOf(10), account.getFoodBalance());
    }

    @Test
    void shouldReduceMealBalance(){
        var account = new Account(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        var transaction = new Transaction(account.getId(), BigDecimal.ONE, "SAO PAULO", MccType.MEAL.getFirstCodeIfExist());

        var transactionStatus = account.execute(transaction);

        assertEquals(TransactionStatus.APPROVED, transactionStatus);
        assertEquals(BigDecimal.valueOf(9), account.getMealBalance());
    }

    @Test
    void shouldNotReduceMealBalance(){
        var account = new Account(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        var transaction = new Transaction(account.getId(), BigDecimal.valueOf(11), "SAO PAULO", MccType.MEAL.getFirstCodeIfExist());

        var transactionStatus = account.execute(transaction);

        assertEquals(TransactionStatus.REJECTED, transactionStatus);
        assertEquals(BigDecimal.valueOf(10), account.getMealBalance());
    }

    @Test
    void shouldReduceCashBalance(){
        var account = new Account(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        var transaction = new Transaction(account.getId(), BigDecimal.ONE, "SAO PAULO", MccType.CASH.getFirstCodeIfExist());

        var transactionStatus = account.execute(transaction);

        assertEquals(TransactionStatus.APPROVED, transactionStatus);
        assertEquals(BigDecimal.valueOf(9), account.getCashBalance());
    }

    @Test
    void shouldNotReduceCashBalance(){
        var account = new Account(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        var transaction = new Transaction(account.getId(), BigDecimal.valueOf(11), "SAO PAULO", MccType.CASH.getFirstCodeIfExist());

        var transactionStatus = account.execute(transaction);

        assertEquals(TransactionStatus.REJECTED, transactionStatus);
        assertEquals(BigDecimal.valueOf(10), account.getCashBalance());
    }

    @Test
    void shouldReduceCashBalanceInsteadFoodBalance(){
        var account = new Account(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.valueOf(11));
        var transaction = new Transaction(account.getId(), BigDecimal.valueOf(11), "SAO PAULO", MccType.FOOD.getFirstCodeIfExist());

        var transactionStatus = account.execute(transaction);

        assertEquals(TransactionStatus.APPROVED, transactionStatus);
        assertEquals(BigDecimal.valueOf(0), account.getCashBalance());
        assertEquals(BigDecimal.TEN, account.getFoodBalance());
    }

    @Test
    void shouldReduceCashBalanceInsteadMealBalance(){
        var account = new Account(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.valueOf(11));
        var transaction = new Transaction(account.getId(), BigDecimal.valueOf(11), "SAO PAULO", MccType.MEAL.getFirstCodeIfExist());

        var transactionStatus = account.execute(transaction);

        assertEquals(TransactionStatus.APPROVED, transactionStatus);
        assertEquals(BigDecimal.valueOf(0), account.getCashBalance());
        assertEquals(BigDecimal.TEN, account.getMealBalance());
    }

}
