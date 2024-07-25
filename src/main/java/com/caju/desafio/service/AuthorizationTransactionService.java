package com.caju.desafio.service;

import com.caju.desafio.dataprovider.repository.AccountRepository;
import com.caju.desafio.domain.MerchantService;
import com.caju.desafio.domain.TransactionResponse;
import com.caju.desafio.domain.TransactionService;
import com.caju.desafio.domain.Transaction;
import com.caju.desafio.mapper.AccountMapper;
import com.caju.desafio.mapper.TransactionMapper;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import static com.caju.desafio.domain.TransactionStatus.APPROVED;
import static com.caju.desafio.domain.TransactionStatus.TRANSACTION_PROBLEM;

@Service
public class AuthorizationTransactionService implements TransactionService {

    private final MerchantService cachedMerchantService;

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AuthorizationTransactionService(MerchantService cachedMerchantService,
                                           AccountRepository accountRepository,
                                           AccountMapper accountMapper) {
        this.cachedMerchantService = cachedMerchantService;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public TransactionResponse execute(Transaction transaction) {

        var merchantMap = cachedMerchantService.getNameMccMap();
        transaction.overrideMccByMerchants(merchantMap);

        var searchedAccount = accountRepository.findById(transaction.getAccountId());

        if (searchedAccount.isEmpty())
            return new TransactionResponse(TRANSACTION_PROBLEM);

        var account = accountMapper.toDomain(searchedAccount.get());

        var transactionStatus = account.execute(transaction);

        if(APPROVED.equals(transactionStatus)){
            var entity = accountMapper.ToEntity(account);
            accountRepository.save(entity);
        }

        return new TransactionResponse(transactionStatus);
    }

}
