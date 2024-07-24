package com.caju.desafio.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class CreditCardTransaction {

    private final String id = UUID.randomUUID().toString();
    private final Long accountId;
    private final BigDecimal amount;
    private final String merchant;
    private final MccType mcc;

    public CreditCardTransaction(Long accountId, BigDecimal amount, String merchant, MccType mcc) {
        this.accountId = accountId;
        this.amount = amount;
        this.merchant = merchant;
        this.mcc = mcc;
    }

    public String getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public MccType getMcc() {
        return mcc;
    }
}
