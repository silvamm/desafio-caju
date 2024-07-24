package com.caju.desafio.config;

import com.caju.desafio.domain.TransactionResponse;
import com.caju.desafio.domain.TransactionStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new TransactionResponse(TransactionStatus.TRANSACTION_PROBLEM);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new TransactionResponse(TransactionStatus.TRANSACTION_PROBLEM);
    }
}
