package com.financial.challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.financial.challenge.domain.util.enums.TransactionStatusEnum;
import com.financial.challenge.domain.util.enums.TransactionTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

  private Long id;
  private TransactionTypeEnum transactionType;
  private BigDecimal amount;
  private BigDecimal gmf;
  private Account originAccount;
  private Account destinationAccount;
  private TransactionStatusEnum status;
  private LocalDateTime createdAt;
}
