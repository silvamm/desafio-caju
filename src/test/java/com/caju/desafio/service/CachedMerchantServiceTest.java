package com.caju.desafio.service;

import com.caju.desafio.dataprovider.entity.MerchantEntity;
import com.caju.desafio.dataprovider.repository.MerchantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CachedMerchantServiceTest {

    @InjectMocks
    private CachedMerchantService cachedMerchantService;

    @Mock
    private MerchantRepository merchantRepository;

    @Test
    public void shouldCreateMapWhereMerchantNameKeyAndMccValue(){

        var merchants = List.of(
                new MerchantEntity(1L, "First", "1"),
                new MerchantEntity(2L, "Second", "2")
        );

        when(merchantRepository.findAll()).thenReturn(merchants);

        var map = cachedMerchantService.getNameMccMap();

        verify(merchantRepository).findAll();
        assertEquals(Map.of("First", "1", "Second", "2"), map);
    }
}
