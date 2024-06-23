package com.financial.challenge.infraestructure.out.jpa.adapter;

import org.springframework.stereotype.Component;

import com.financial.challenge.domain.model.Transaction;
import com.financial.challenge.domain.spi.TransactionPersistencePort;
import com.financial.challenge.infraestructure.out.jpa.entity.TransactionEntity;
import com.financial.challenge.infraestructure.out.jpa.mapper.TransactionEntityMapper;
import com.financial.challenge.infraestructure.out.jpa.mapper.TransactionMapper;
import com.financial.challenge.infraestructure.out.jpa.repository.TransactionRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TransactionPersistenceAdapter implements TransactionPersistencePort {

  private final TransactionRepository transactionRepository;
  private final TransactionEntityMapper transactionEntityMapper;
  private final TransactionMapper transactionMapper;

  @Override
  public Transaction save(Transaction transaction) {
    TransactionEntity transactionEntity = transactionMapper.toTransactionEntity(transaction);
    TransactionEntity transactionEntityCreated = transactionRepository.save(transactionEntity);
    return transactionEntityMapper.toTransaction(transactionEntityCreated);
  }
}
