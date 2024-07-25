package com.caju.desafio.service;

import com.caju.desafio.dataprovider.entity.AccountEntity;
import com.caju.desafio.dataprovider.repository.AccountRepository;
import com.caju.desafio.domain.*;
import com.caju.desafio.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorizationTransactionServiceTest {

    @InjectMocks
    private AuthorizationTransactionService authorizationTransactionService;

    @Mock
    private MerchantService merchantService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @Test
    public void shouldSaveAccountThatExecutedTransaction(){
        var account = new Account(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        var accountEntity = new AccountEntity(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        var transaction = new Transaction(1L, BigDecimal.ONE, "SAO PAULO", MccType.FOOD.getFirstCodeIfExist());

        when(merchantService.getNameMccMap()).thenReturn(Map.of());
        when(accountRepository.findById(transaction.getAccountId())).thenReturn(Optional.of(accountEntity));
        when(accountMapper.toDomain(accountEntity)).thenReturn(account);
        when(accountMapper.toEntity(account)).thenReturn(accountEntity);

        var transactionResponse = authorizationTransactionService.execute(transaction);

        verify(accountRepository).save(accountEntity);
        verify(accountRepository).findById(transaction.getAccountId());
        verify(merchantService).getNameMccMap();
        assertEquals(TransactionStatus.APPROVED.getCode(), transactionResponse.getCode());
    }

    @Test
    public void shouldEscapeIfAccountNotFound(){
        var transaction = new Transaction(1L, BigDecimal.ONE, "SAO PAULO", MccType.FOOD.getFirstCodeIfExist());

        when(merchantService.getNameMccMap()).thenReturn(Map.of());
        when(accountRepository.findById(transaction.getAccountId())).thenReturn(Optional.empty());

        var transactionResponse = authorizationTransactionService.execute(transaction);

        verify(accountRepository).findById(transaction.getAccountId());
        verify(merchantService).getNameMccMap();
        assertEquals(TransactionStatus.TRANSACTION_PROBLEM.getCode(), transactionResponse.getCode());
    }

    @Test
    public void shouldNotSaveAccountIfTransactionAccountReturnsRejacted(){
        var account = new Account(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        var accountEntity = new AccountEntity(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        var transaction = new Transaction(1L, BigDecimal.valueOf(11), "SAO PAULO", MccType.FOOD.getFirstCodeIfExist());

        when(merchantService.getNameMccMap()).thenReturn(Map.of());
        when(accountRepository.findById(transaction.getAccountId())).thenReturn(Optional.of(accountEntity));
        when(accountMapper.toDomain(accountEntity)).thenReturn(account);

        var transactionResponse = authorizationTransactionService.execute(transaction);

        verify(accountRepository, times(0)).save(accountEntity);
        verify(accountRepository).findById(transaction.getAccountId());
        verify(merchantService).getNameMccMap();
        assertEquals(TransactionStatus.REJECTED.getCode(), transactionResponse.getCode());
    }
}
