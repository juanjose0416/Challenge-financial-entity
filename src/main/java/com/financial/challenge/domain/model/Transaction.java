package com.financial.challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.financial.challenge.domain.util.TransactionTypeEnum;

import lombok.Data;

@Data
public class Transaction {

    private Long id;
    private TransactionTypeEnum transactionType;
    private BigDecimal amount;
    private String originAccount;
    private String destinationAccount;
    private LocalDateTime createdAt;

}
