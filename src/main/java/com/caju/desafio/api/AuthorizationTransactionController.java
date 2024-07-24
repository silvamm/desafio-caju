package com.caju.desafio.api;

import com.caju.desafio.api.dto.PutRequestTransaction;
import com.caju.desafio.domain.*;
import com.caju.desafio.mapper.TransactionMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationTransactionController {

    private final TransactionService authorizationTransactionService;
    private final TransactionMapper transactionMapper;

    public AuthorizationTransactionController(TransactionService authorizationTransactionService, TransactionMapper transactionMapper) {
        this.authorizationTransactionService = authorizationTransactionService;
        this.transactionMapper = transactionMapper;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse authorize(@Valid @RequestBody PutRequestTransaction request) {
        var transaction = transactionMapper.toDomain(request);
        return authorizationTransactionService.execute(transaction);
    }

}
