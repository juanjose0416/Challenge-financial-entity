package com.financial.challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;

public class CurrentAccount extends Account {

  private static final String PREFIX = "33";

  public CurrentAccount(
      Long id, String accountNumber, BigDecimal balance, boolean isGMF, Client client) {
    super(
        id,
        AccountTypeEnum.CURRENT_ACCOUNT,
        PREFIX + accountNumber,
        StatusEnum.INACTIVE,
        balance,
        isGMF,
        LocalDateTime.now(),
        LocalDateTime.now(),
        client);
  }
}
