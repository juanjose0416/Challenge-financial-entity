package com.financial.challenge.domain.service;

import java.util.List;

import com.financial.challenge.domain.model.Account;

public interface AccountService {

  Account save(Account account);

  Account getAccountById(Long accountId) throws Exception;

  void updateAccount(Account account);

  void delete(Long accountId);

  List<Account> getAll();

  Account getAccount(String numberAccount) throws Exception;
}
