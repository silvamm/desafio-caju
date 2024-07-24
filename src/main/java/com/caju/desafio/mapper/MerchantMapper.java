package com.caju.desafio.mapper;

import com.caju.desafio.dataprovider.entity.MerchantEntity;
import com.caju.desafio.domain.Merchant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MerchantMapper {

    Merchant toDomain(MerchantEntity entity);
}
