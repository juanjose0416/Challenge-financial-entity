package com.financial.challenge.app.usecase.account;

import com.financial.challenge.app.dto.account.request.CreateAccountRequest;
import com.financial.challenge.app.dto.account.response.AccountResponse;

public interface AccountCreatorUseCase {

    AccountResponse createAccount(CreateAccountRequest request) throws Exception;
}
