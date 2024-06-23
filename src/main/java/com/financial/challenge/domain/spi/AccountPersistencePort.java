package com.financial.challenge.domain.spi;

import com.financial.challenge.domain.model.Account;

public interface AccountPersistencePort {

    Account create(Account account);
}
