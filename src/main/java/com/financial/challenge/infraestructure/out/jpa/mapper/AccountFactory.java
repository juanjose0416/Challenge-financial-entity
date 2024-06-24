package com.financial.challenge.infraestructure.out.jpa.mapper;

import java.util.List;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.infraestructure.out.jpa.entity.AccountEntity;
import com.financial.challenge.infraestructure.out.jpa.entity.ClientEntity;

public interface AccountFactory {

    Account buildAccount(AccountEntity accountEntity, Client client);

    List<Account> getAccounts(List<AccountEntity> accountEntityList);
}
