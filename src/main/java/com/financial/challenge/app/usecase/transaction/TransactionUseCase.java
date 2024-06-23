package com.financial.challenge.app.usecase.transaction;

import com.financial.challenge.app.dto.transaction.request.DepositRequest;
import com.financial.challenge.app.dto.transaction.request.TransactionRequest;
import com.financial.challenge.app.dto.transaction.response.TransactionResponse;

public interface TransactionUseCase {

    TransactionResponse deposit(DepositRequest request) throws Exception;

    TransactionResponse withdraw(DepositRequest request) throws Exception;

    TransactionResponse transfer(TransactionRequest request) throws Exception;
}
