package com.financial.challenge.domain.spi;

import java.util.Optional;

import com.financial.challenge.domain.model.Account;

public interface AccountPersistencePort {

  Account save(Account account);

  Optional<Account> getAccountById(Long accountId);
}
