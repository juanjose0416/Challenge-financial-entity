package com.financial.challenge.app.pattern.builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Transaction;
import com.financial.challenge.domain.util.enums.TransactionStatusEnum;
import com.financial.challenge.domain.util.enums.TransactionTypeEnum;

public class TransactionBuilder {

  private TransactionBuilder() {}

  public static Transaction.TransactionBuilder buildTransaction(
      BigDecimal amount,
      BigDecimal gmf,
      Account originAccount,
      Account targetAccount) {
    return Transaction.builder()
        .amount(amount)
        .gmf(gmf)
        .originAccount(originAccount)
        .destinationAccount(targetAccount)
        .createdAt(LocalDateTime.now());
  }
}
