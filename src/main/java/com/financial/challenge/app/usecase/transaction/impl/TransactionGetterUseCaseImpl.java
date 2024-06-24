package com.financial.challenge.app.usecase.transaction.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.financial.challenge.app.dto.transaction.response.TransactionResponse;
import com.financial.challenge.app.mapper.AppTransactionResponseMapper;
import com.financial.challenge.app.usecase.transaction.TransactionGetterUseCase;
import com.financial.challenge.domain.service.TransactionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionGetterUseCaseImpl implements TransactionGetterUseCase {

  private final TransactionService transactionService;
  private final AppTransactionResponseMapper appTransactionResponseMapper;

  @Override
  public TransactionResponse getTransaction(Long transactionId) {
    return appTransactionResponseMapper.toTransactionResponse(
        transactionService.getTransaction(transactionId));
  }

  @Override
  public List<TransactionResponse> findAll() {
    return appTransactionResponseMapper.toTransactionResponseList(transactionService.findAll());
  }
}
