package com.caju.desafio.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record PutRequestTransaction(
        @NotNull
        Long account,
        @NotNull
        @DecimalMin(value = "0")
        BigDecimal totalAmount,
        @NotNull
        @NotBlank
        String mcc,
        @NotNull
        @NotBlank
        String merchant) {

}
