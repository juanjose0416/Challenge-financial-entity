package com.financial.challenge.domain.pattern.strategy;

import java.math.BigDecimal;

import com.financial.challenge.domain.model.Account;

public interface TransactionStrategy {

  void execute(Account sourceAccount, BigDecimal amount, Account targetAccount) throws Exception;
}
