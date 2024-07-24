package com.caju.desafio.mapper;

import com.caju.desafio.api.dto.PutRequestTransaction;
import com.caju.desafio.domain.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "account", target = "accountId")
    @Mapping(source = "totalAmount", target = "amount")
    Transaction toDomain(PutRequestTransaction transaction);
}
