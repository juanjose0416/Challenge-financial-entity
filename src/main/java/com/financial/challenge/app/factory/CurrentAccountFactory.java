package com.financial.challenge.app.factory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.model.CurrentAccount;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;
import com.financial.challenge.domain.util.generator.AccountNumberGenerator;

@Component("CurrentAccountFactory")
public class CurrentAccountFactory implements AccountFactory {

  private static final String PREFIX = "33";

  @Override
  public Account create(Boolean isGMF, Client client) {
    return new CurrentAccount(
        null,
        AccountTypeEnum.CURRENT_ACCOUNT,
        PREFIX + AccountNumberGenerator.generateNumber(),
        StatusEnum.INACTIVE,
        BigDecimal.ZERO,
        isGMF,
        LocalDateTime.now(),
        LocalDateTime.now(),
        client);
  }
}
