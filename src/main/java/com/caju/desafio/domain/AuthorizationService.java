package com.caju.desafio.domain;

public interface AuthorizationService {

    AuthorizationResponse execute(CreditCardPayloadTransaction transaction);

}
