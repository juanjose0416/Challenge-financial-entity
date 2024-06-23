package com.financial.challenge.app.usecase.account.impl;

import org.springframework.stereotype.Service;

import com.financial.challenge.app.dto.account.response.AccountResponse;
import com.financial.challenge.app.usecase.account.AccountCreatorUseCase;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountCreatorUseCaseImpl implements AccountCreatorUseCase {

    @Override
    public AccountResponse createAccount() {
        return null;
    }

}
