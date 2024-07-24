package com.caju.desafio.service;

import com.caju.desafio.dataprovider.repository.MerchantRepository;
import com.caju.desafio.domain.MerchantService;
import com.caju.desafio.mapper.MerchantMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CachedMerchantService implements MerchantService {

    private final MerchantRepository merchantRepository;

    public CachedMerchantService(MerchantRepository merchantRepository, MerchantMapper merchantMapper) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    @Cacheable("merchants")
    public Map<String, String> getNameMccMap() {
        return merchantRepository.findAll()
                .stream()
                .collect(HashMap::new, (map, merchant) -> map.put(merchant.getName(), merchant.getMcc()), HashMap::putAll);
    }
}
