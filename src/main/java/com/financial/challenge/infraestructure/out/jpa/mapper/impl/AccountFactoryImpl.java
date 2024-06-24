package com.financial.challenge.infraestructure.out.jpa.mapper.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.financial.challenge.domain.exception.AccountException;
import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.model.CurrentAccount;
import com.financial.challenge.domain.model.SavingAccount;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.infraestructure.out.jpa.entity.AccountEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountFactoryImpl {

  public static Account buildAccount(AccountEntity accountEntity, Client client) {
    if (Objects.isNull(accountEntity)) {
      return null;
    }
    if (AccountTypeEnum.CURRENT_ACCOUNT.equals(accountEntity.getAccountType())) {
      return CurrentAccount.builder()
          .id(accountEntity.getId())
          .accountType(accountEntity.getAccountType())
          .accountNumber(accountEntity.getAccountNumber())
          .status(accountEntity.getStatus())
          .balance(accountEntity.getBalance())
          .isGMFFree(accountEntity.isGMFFree())
          .createdAt(accountEntity.getCreatedAt())
          .updatedAt(accountEntity.getUpdatedAt())
          .client(client)
          .build();
    } else if (AccountTypeEnum.SAVING_ACCOUNT.equals(accountEntity.getAccountType())) {
      return new SavingAccount(
          accountEntity.getId(),
          accountEntity.getAccountType(),
          accountEntity.getAccountNumber(),
          accountEntity.getStatus(),
          accountEntity.getBalance(),
          accountEntity.isGMFFree(),
          accountEntity.getCreatedAt(),
          accountEntity.getUpdatedAt(),
          client);
    } else {
      throw new AccountException("Unknown account type");
    }
  }

  public static List<Account> getAccounts(List<AccountEntity> accountEntityList) {
    if (Objects.isNull(accountEntityList)) {
      return Collections.emptyList();
    }
    return accountEntityList.stream()
        .map(
            accountEntity ->
                buildAccount(
                    accountEntity,
                    ClientBuilderImpl.getClientWithoutAccount(accountEntity.getClient())))
        .collect(Collectors.toList());
  }

  public static List<Account> getAccountsWithoutClients(List<AccountEntity> accountEntityList) {
    if (Objects.isNull(accountEntityList)) {
      return Collections.emptyList();
    }
    return accountEntityList.stream()
        .map(accountEntity -> buildAccount(accountEntity, null))
        .collect(Collectors.toList());
  }
}
