package com.financial.challenge.infraestructure.out.jpa.mapper.impl;

import com.financial.challenge.domain.model.Transaction;
import com.financial.challenge.infraestructure.out.jpa.entity.TransactionEntity;

public class TransactionBuilderImpl {

  public static Transaction toModel(TransactionEntity transactionEntity) {
    return Transaction.builder()
        .id(transactionEntity.getId())
        .transactionType(transactionEntity.getTransactionType())
        .amount(transactionEntity.getAmount())
        .gmf(transactionEntity.getGmf())
        .originAccount(AccountFactoryImpl.buildAccount(transactionEntity.getOriginAccount(), null))
        .destinationAccount(
            AccountFactoryImpl.buildAccount(transactionEntity.getDestinationAccount(), null))
        .status(transactionEntity.getStatus())
        .createdAt(transactionEntity.getCreatedAt())
        .build();
  }
}
