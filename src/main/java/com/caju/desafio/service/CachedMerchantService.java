package com.caju.desafio.service;

import com.caju.desafio.dataprovider.entity.MerchantEntity;
import com.caju.desafio.dataprovider.repository.MerchantRepository;
import com.caju.desafio.domain.MerchantService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CachedMerchantService implements MerchantService {

    private final MerchantRepository merchantRepository;

    public CachedMerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    @Cacheable("merchants")
    public Map<String, String> getNameMccMap() {
        return merchantRepository.findAll()
                .stream()
                .collect(Collectors.toMap(MerchantEntity::getName, MerchantEntity::getMcc));
    }
}
