package com.caju.desafio.domain;

public class TransactionResponse {

    private final String code;

    public TransactionResponse(TransactionStatus status){
        this.code = status.getCode();
    }

    public String getCode() {
        return code;
    }
}
