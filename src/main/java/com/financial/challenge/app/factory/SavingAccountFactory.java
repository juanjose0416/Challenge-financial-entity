package com.financial.challenge.app.factory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.model.SavingAccount;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;
import com.financial.challenge.domain.util.generator.AccountNumberGenerator;

@Component("SavingAccountFactory")
public class SavingAccountFactory implements AccountFactory {

  private static final String PREFIX = "53";

  @Override
  public Account create(Boolean isGMF, Client client) {
    return new SavingAccount(
        null,
        AccountTypeEnum.SAVING_ACCOUNT,
        PREFIX + AccountNumberGenerator.generateNumber(),
        StatusEnum.ACTIVE,
        BigDecimal.ZERO,
        isGMF,
        LocalDateTime.now(),
        LocalDateTime.now(),
        client);
  }
}
