package com.financial.challenge.app.usecase.transaction.impl;

import org.springframework.stereotype.Service;

import com.financial.challenge.app.usecase.transaction.TransactionDeleterUseCase;
import com.financial.challenge.domain.service.TransactionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionDeleterUseCaseImpl implements TransactionDeleterUseCase {

  private final TransactionService transactionService;

  @Override
  public void deleteTransaction(Long transactionId) {
    transactionService.getTransaction(transactionId);
    transactionService.deleteTransaction(transactionId);
  }
}
