package com.financial.challenge.domain.pattern.strategy;

import java.math.BigDecimal;

import com.financial.challenge.domain.exception.AccountException;
import com.financial.challenge.domain.model.Account;

public class WithDrawStrategy implements TransactionStrategy {

  @Override
  public void execute(Account account, BigDecimal amount, Account targetAccount)
      throws AccountException {
    account.withdraw(amount);
  }
}
