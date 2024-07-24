package com.caju.desafio.mapper;

import com.caju.desafio.dataprovider.entity.AccountEntity;
import com.caju.desafio.domain.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountEntity ToEntity(Account account);

    Account toDomain(AccountEntity entity);
}
