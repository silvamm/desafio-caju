package com.caju.desafio.api.dto;

import java.math.BigDecimal;

public record RequestTransaction(
        String account,
        BigDecimal totalAmount,
        String mcc,
        String merchant) {

}
