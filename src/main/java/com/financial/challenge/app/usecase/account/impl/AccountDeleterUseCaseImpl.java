package com.financial.challenge.app.usecase.account.impl;

import org.springframework.stereotype.Service;

import com.financial.challenge.app.usecase.account.AccountDeleterUseCase;
import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.service.AccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountDeleterUseCaseImpl implements AccountDeleterUseCase {

  private final AccountService accountService;

  @Override
  public void deleteAccount(Long accountId) {
    Account account = accountService.getAccountById(accountId);
    account.cancel();
    accountService.delete(accountId);
  }
}
