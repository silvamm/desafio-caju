package com.caju.desafio.domain;

import java.math.BigDecimal;

public class CreditCardPayloadTransaction {

    private String id;
    private Account account;
    private BigDecimal amount;
    private String merchant;
    private String mcc;

    public String getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getMcc() {
        return mcc;
    }
}
