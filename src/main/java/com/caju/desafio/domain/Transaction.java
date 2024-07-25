package com.caju.desafio.domain;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public class Transaction {

    private final String id = UUID.randomUUID().toString();
    private final Long accountId;
    private final BigDecimal amount;
    private final String merchant;
    private String mcc;

    public Transaction(Long accountId, BigDecimal amount, String merchant, String mcc) {
        this.accountId = accountId;
        this.amount = amount;
        this.merchant = merchant;
        this.mcc = mcc;
    }

    public void overrideMccByMerchants(Map<String, String> merchantNameMccMap){
        var foundMcc = merchantNameMccMap.get(getMerchant());
        if(foundMcc != null)
            this.mcc = foundMcc;

    }

    public String getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getMcc() {
        return mcc;
    }

    public MccType getMccType(){
        return MccType.findByCode(mcc);
    }
}
