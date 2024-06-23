package com.financial.challenge.domain.pattern.factory;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.CurrentAccount;
import com.financial.challenge.domain.model.SavingAccount;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;

public class AccountFactory {

  public static Account getAccountType(Account account) {
    if (AccountTypeEnum.CURRENT_ACCOUNT.equals(account.getAccountType())) {
      return new CurrentAccount(
          account.getId(),
          account.getAccountType(),
          account.getAccountNumber(),
          account.getStatus(),
          account.getBalance(),
          account.isGMFFree(),
          account.getCreatedAt(),
          account.getUpdatedAt(),
          account.getClient());
    } else {
      return new SavingAccount(
          account.getId(),
          account.getAccountType(),
          account.getAccountNumber(),
          account.getStatus(),
          account.getBalance(),
          account.isGMFFree(),
          account.getCreatedAt(),
          account.getUpdatedAt(),
          account.getClient());
    }
  }
}
