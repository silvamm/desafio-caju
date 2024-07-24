package com.caju.desafio.api;


import com.caju.desafio.api.dto.RequestTransaction;
import com.caju.desafio.domain.AuthorizationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.caju.desafio.domain.TransactionStatus.APPROVED;

@RestController
public class AuthorizationController {

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public AuthorizationResponse authorize(RequestTransaction request) {





        return new AuthorizationResponse(APPROVED);
    }

}
