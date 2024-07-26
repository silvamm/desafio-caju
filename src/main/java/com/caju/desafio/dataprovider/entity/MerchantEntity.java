package com.caju.desafio.dataprovider.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "merchant")
public class MerchantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String mcc;

    public MerchantEntity() {
    }

    public MerchantEntity(Long id, String name, String mcc) {
        this.id = id;
        this.name = name;
        this.mcc = mcc;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMcc() {
        return mcc;
    }
}
