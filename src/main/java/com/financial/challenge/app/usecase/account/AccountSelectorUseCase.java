package com.financial.challenge.app.usecase.account;

import java.util.List;

import com.financial.challenge.app.dto.account.response.AccountResponse;

public interface AccountSelectorUseCase {
  AccountResponse getAccount(Long accountId) throws Exception;

  List<AccountResponse> getAll();
}
