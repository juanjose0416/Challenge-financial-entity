package com.financial.challenge.domain.service.impl;

import org.springframework.stereotype.Service;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.service.AccountService;
import com.financial.challenge.domain.spi.AccountPersistencePort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountPersistencePort accountPersistencePort;

    @Override
    public Account createAccount(Account account) {
        return accountPersistencePort.create(account);
    }

}
