package com.financial.challenge.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.financial.challenge.domain.exception.TransactionException;
import com.financial.challenge.domain.model.Transaction;
import com.financial.challenge.domain.service.TransactionService;
import com.financial.challenge.domain.spi.TransactionPersistencePort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionPersistencePort transactionPersistencePort;

  @Override
  public Transaction execute(Transaction transaction) {
    return transactionPersistencePort.save(transaction);
  }

  @Override
  public Transaction getTransaction(Long transactionId) {
    return transactionPersistencePort
        .findTransactionById(transactionId)
        .orElseThrow(() -> new TransactionException("Transaction doesn't exist"));
  }

  @Override
  public List<Transaction> findAll() {
    return transactionPersistencePort.findAll();
  }

  @Override
  public void deleteTransaction(Long accountId) {
    transactionPersistencePort.deleteById(accountId);
  }
}
