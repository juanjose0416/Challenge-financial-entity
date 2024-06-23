package com.financial.challenge.domain.service.impl;

import java.util.Optional;

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
    return accountPersistencePort.save(account);
  }

  @Override
  public Account getAccountById(Long accountId) throws Exception {
    Optional<Account> account = accountPersistencePort.getAccountById(accountId);
    if (account.isPresent()) {
      return account.get();
    }
    throw new Exception("Account doesn't exist");
  }

  @Override
  public void updateAccount(Account account) {
    accountPersistencePort.save(account);
  }
}
