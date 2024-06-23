package com.financial.challenge.domain.pattern.strategy;

import java.math.BigDecimal;

import com.financial.challenge.domain.exception.AccountException;
import com.financial.challenge.domain.model.Account;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransferStrategy implements TransactionStrategy {

  @Override
  public void execute(Account sourceAccount, BigDecimal amount, Account targetAccount)
      throws AccountException {
    sourceAccount.transfer(targetAccount, amount);
  }
}
