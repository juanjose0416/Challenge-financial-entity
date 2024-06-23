package com.financial.challenge.app.usecase.account;

import com.financial.challenge.app.dto.account.request.UpdateAccountRequest;

public interface AccountUpdaterUseCase {

    void updateAccount(UpdateAccountRequest request, Long accountId) throws Exception;
}
