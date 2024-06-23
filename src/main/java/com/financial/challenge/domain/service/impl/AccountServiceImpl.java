package com.financial.challenge.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.financial.challenge.domain.exception.AccountException;
import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.pattern.factory.AccountFactory;
import com.financial.challenge.domain.service.AccountService;
import com.financial.challenge.domain.spi.AccountPersistencePort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountPersistencePort accountPersistencePort;

  @Override
  @Transactional
  public Account save(Account account) {
    return accountPersistencePort.save(account);
  }

  @Override
  public Account getAccountById(Long accountId) throws AccountException {
    Optional<Account> account = accountPersistencePort.getAccountById(accountId);
    if (account.isPresent()) {
      return AccountFactory.getAccountType(account.get());
    }
    throw new AccountException("Account doesn't exist");
  }

  @Override
  public void updateAccount(Account account) {
    accountPersistencePort.save(account);
  }

  @Override
  public void delete(Long accountId) {
    accountPersistencePort.deleteById(accountId);
  }

  @Override
  public List<Account> getAll() {
    return accountPersistencePort.getAll();
  }

  @Override
  public Account getAccount(String accountNumber) throws AccountException {
    Optional<Account> account = accountPersistencePort.getAccount(accountNumber);
    if (account.isPresent()) {
      return AccountFactory.getAccountType(account.get());
    }
    throw new AccountException(String.format("Account %s doesn't exist", accountNumber));
  }
}
