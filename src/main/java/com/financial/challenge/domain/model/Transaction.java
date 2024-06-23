package com.financial.challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.financial.challenge.domain.util.enums.TransactionTypeEnum;

import lombok.Data;

@Data
public class Transaction {

  private Long id;
  private TransactionTypeEnum transactionType;
  private BigDecimal amount;
  private Account originAccount;
  private Account destinationAccount;
  private LocalDateTime createdAt;
}
