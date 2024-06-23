package com.financial.challenge.domain.pattern.strategy;

import java.math.BigDecimal;
import java.util.Optional;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.spi.AccountPersistencePort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransferStrategy implements TransactionStrategy {

  @Override
  public void execute(Account sourceAccount, BigDecimal amount, Account targetAccount)
      throws Exception {
    sourceAccount.transfer(targetAccount, amount);
  }

}
