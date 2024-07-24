package com.caju.desafio.domain;

import java.util.Collections;
import java.util.List;

public enum MccType {

    FOOD(List.of("5411", "5412")),
    MEAL(List.of("5811", "5812")),
    CASH(Collections.emptyList());

    private final List<String> codes;

    MccType(List<String> codes) {
        this.codes = codes;
    }

    public static MccType findByCode(String code) {
        for (MccType mccType : MccType.values()) {
            if(mccType.codes.contains(code))
                return mccType;
        }
        return CASH;
    }

}
