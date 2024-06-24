package com.financial.challenge.domain.pattern.strategy;

import java.math.BigDecimal;

import com.financial.challenge.domain.model.Account;

public class DepositStrategy implements TransactionStrategy {

  @Override
  public void execute(Account account, BigDecimal amount, Account targetAccount) {
    account.deposit(amount);
    account.activate();
  }
}
