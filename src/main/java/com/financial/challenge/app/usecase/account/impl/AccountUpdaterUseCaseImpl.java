package com.financial.challenge.app.usecase.account.impl;

import org.springframework.stereotype.Service;

import com.financial.challenge.app.dto.account.request.UpdateAccountRequest;
import com.financial.challenge.app.usecase.account.AccountUpdaterUseCase;
import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.service.AccountService;
import com.financial.challenge.domain.util.enums.StatusEnum;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountUpdaterUseCaseImpl implements AccountUpdaterUseCase {

  private final AccountService accountService;

  @Override
  public void updateAccount(UpdateAccountRequest request, Long accountId) {
    Account account = accountService.getAccountById(accountId);
    updateStatus(request.getStatus(), account);
    accountService.updateAccount(account);
  }

  private void updateStatus(StatusEnum status, Account account) {
    if (StatusEnum.ACTIVE.equals(status)) {
      account.activate();
    } else if (StatusEnum.INACTIVE.equals(status)) {
      account.inactivate();
    } else {
      account.cancel();
    }
  }
}
