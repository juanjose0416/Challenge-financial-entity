package com.financial.challenge.infraestructure.out.jpa.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.financial.challenge.domain.exception.AccountException;
import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.spi.AccountPersistencePort;
import com.financial.challenge.infraestructure.out.jpa.entity.AccountEntity;
import com.financial.challenge.infraestructure.out.jpa.mapper.AccountMapper;
import com.financial.challenge.infraestructure.out.jpa.mapper.impl.AccountFactoryImpl;
import com.financial.challenge.infraestructure.out.jpa.mapper.impl.ClientBuilderImpl;
import com.financial.challenge.infraestructure.out.jpa.repository.AccountRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Transactional
public class AccountPersistenceAdapter implements AccountPersistencePort {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;

  @Override
  public Account save(Account account) {
    AccountEntity accountEntity = accountMapper.accountToAccountEntity(account);
    AccountEntity accountCreated = accountRepository.save(accountEntity);
    Client client = ClientBuilderImpl.toClient(accountCreated.getClient());
    return AccountFactoryImpl.buildAccount(accountCreated, client);
  }

  @Override
  public Optional<Account> getAccountById(Long accountId) {
    return accountRepository
        .findById(accountId)
        .map(
            accountEntity ->
                AccountFactoryImpl.buildAccount(
                    accountEntity, ClientBuilderImpl.toClient(accountEntity.getClient())));
  }

  @Override
  @Transactional
  public void deleteById(Long accountId) {
    AccountEntity account =
        accountRepository
            .findById(accountId)
            .orElseThrow(() -> new AccountException("Account not found"));
    accountRepository.delete(account);
    accountRepository.deleteAll();
  }

  @Override
  public List<Account> getAll() {
    return AccountFactoryImpl.getAccounts(accountRepository.findAll());
  }

  @Override
  public Optional<Account> getAccount(String accountNumber) {
    return accountRepository
        .findByAccountNumber(accountNumber)
        .map(
            accountEntity ->
                AccountFactoryImpl.buildAccount(
                    accountEntity, ClientBuilderImpl.toClient(accountEntity.getClient())));
  }
}
