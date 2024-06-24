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
      AccountTypeEnum accountType,
      String accountNumber,
      StatusEnum status,
      BigDecimal balance,
      boolean isGMFFree,
      LocalDateTime createdAt,
      LocalDateTime updatedAt,
      Client client) {
    super(id, accountType, accountNumber, status, balance, isGMFFree, createdAt, updatedAt, client);
  }

  @Override
  public void withdraw(BigDecimal amount) {
    this.balance = this.balance.subtract(amount);
    this.updatedAt = LocalDateTime.now();
  }
}
