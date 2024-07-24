package com.caju.desafio.api.dto;

import java.math.BigDecimal;


public record PutRequestTransaction(
        Long account,
        BigDecimal totalAmount,
        String mcc,
        String merchant) {

}
