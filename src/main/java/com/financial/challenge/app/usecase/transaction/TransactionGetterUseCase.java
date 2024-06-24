package com.financial.challenge.app.usecase.transaction;

import java.util.List;

import com.financial.challenge.app.dto.transaction.response.TransactionResponse;

public interface TransactionGetterUseCase {

    TransactionResponse getTransaction(Long transactionId);

    List<TransactionResponse> findAll();
}
