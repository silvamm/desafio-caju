package com.caju.desafio.domain;

public class AuthorizationResponse {

    private final String code;

    public AuthorizationResponse(TransactionStatus status){
        this.code = status.getCode();
    }

    public String getCode() {
        return code;
    }
}
