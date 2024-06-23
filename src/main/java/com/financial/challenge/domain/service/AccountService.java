package com.financial.challenge.domain.service;

import com.financial.challenge.domain.model.Account;

public interface AccountService {

    Account createAccount(Account account);

    Account getAccountById(Long accountId) throws Exception;

    void updateAccount(Account account);
}
