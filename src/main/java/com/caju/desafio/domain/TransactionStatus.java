package com.caju.desafio.domain;

public enum TransactionStatus {

    APPROVED("00"),
    REJECTED("51"),
    TRANSACTION_PROBLEM("07");

    private final String code;

    TransactionStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
