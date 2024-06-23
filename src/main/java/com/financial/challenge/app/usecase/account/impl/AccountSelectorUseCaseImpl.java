package com.financial.challenge.app.usecase.account.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.financial.challenge.app.dto.account.response.AccountResponse;
import com.financial.challenge.app.mapper.AccountResponseMapper;
import com.financial.challenge.app.usecase.account.AccountSelectorUseCase;
import com.financial.challenge.domain.service.AccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountSelectorUseCaseImpl implements AccountSelectorUseCase {

  private final AccountResponseMapper accountResponseMapper;
  private final AccountService accountService;

  @Override
  public AccountResponse getAccount(Long accountId) {
    return accountResponseMapper.accountToAccountResponse(accountService.getAccountById(accountId));
  }

  @Override
  public List<AccountResponse> getAll() {
    return accountResponseMapper.accountsToAccountResponses(accountService.getAll());
  }
}
