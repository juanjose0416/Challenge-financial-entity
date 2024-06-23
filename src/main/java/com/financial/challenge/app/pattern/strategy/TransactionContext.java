package com.financial.challenge.app.pattern.strategy;

import java.math.BigDecimal;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.pattern.strategy.TransactionStrategy;

public class TransactionContext {

  private TransactionStrategy transactionStrategy;

  public void setTransactionStrategy(TransactionStrategy transactionStrategy) {
    this.transactionStrategy = transactionStrategy;
  }

  public void executeStrategy(Account sourceAccount, BigDecimal amount, Account targetAccount) throws Exception {
    transactionStrategy.execute(sourceAccount, amount, targetAccount);
  }
}
