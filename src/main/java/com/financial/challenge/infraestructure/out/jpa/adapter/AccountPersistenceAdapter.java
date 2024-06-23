package com.financial.challenge.infraestructure.out.jpa.adapter;

import org.springframework.stereotype.Component;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.spi.AccountPersistencePort;
import com.financial.challenge.infraestructure.out.jpa.entity.AccountEntity;
import com.financial.challenge.infraestructure.out.jpa.mapper.AccountEntityMapper;
import com.financial.challenge.infraestructure.out.jpa.mapper.AccountMapper;
import com.financial.challenge.infraestructure.out.jpa.repository.AccountRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AccountPersistenceAdapter implements AccountPersistencePort {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountEntityMapper accountEntityMapper;

    @Override
    public Account create(Account account) {
        AccountEntity accountEntity = accountMapper.accountToAccountEntity(account);
        AccountEntity accountCreated = accountRepository.save(accountEntity);
        return accountEntityMapper.accountEntityToAccount(accountCreated);
    }

}
