package com.financial.challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;

public class SavingAccount extends Account {

  private static final String PREFIX = "53";

  public SavingAccount(
      Long id,
      String accountNumber,
      BigDecimal balance,
      boolean isGMF,
      LocalDateTime createdAt,
      LocalDateTime updatedAt,
      Client client) {
    super(id, AccountTypeEnum.SAVING_ACCOUNT, PREFIX + accountNumber, StatusEnum.ACTIVE, balance, isGMF, createdAt, updatedAt, client);
    validateBalance();
  }

  public void validateBalance(){
    if (this.balance.compareTo(BigDecimal.ZERO) < 0){
      throw new IllegalArgumentException("Balance lees than 0");
    }
  }

}
