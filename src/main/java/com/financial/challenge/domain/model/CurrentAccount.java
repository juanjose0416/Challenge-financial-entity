package com.financial.challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;

import lombok.Builder;

public class CurrentAccount extends Account {

  @Builder
  public CurrentAccount(
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
  }

  @Override
  public void withdraw(BigDecimal amount) {
    this.balance = this.balance.subtract(amount);
    this.updatedAt = LocalDateTime.now();
  }
}
