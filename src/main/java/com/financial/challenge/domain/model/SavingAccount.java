package com.financial.challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.financial.challenge.domain.exception.AccountException;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;

public class SavingAccount extends Account {

  public SavingAccount(
      Long id,
      AccountTypeEnum accountTypeEnum,
      String accountNumber,
      StatusEnum status,
      BigDecimal balance,
      boolean isGMF,
      LocalDateTime createdAt,
      LocalDateTime updatedAt,
      Client client) {
    super(id, accountTypeEnum, accountNumber, status, balance, isGMF, createdAt, updatedAt, client);
    validateBalance();
  }

  public void validateBalance() {
    if (this.balance.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Balance lees than 0");
    }
  }

  @Override
  public void withdraw(BigDecimal amount) throws AccountException {
    if (this.balance.compareTo(amount) < 0) {
      throw new AccountException("Insufficient funds");
    }
    this.balance = this.balance.subtract(amount);
    this.updatedAt = LocalDateTime.now();
  }
}
