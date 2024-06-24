package com.financial.challenge.domain.service;

import java.util.List;

import com.financial.challenge.domain.model.Transaction;

public interface TransactionService {

    Transaction execute(Transaction transaction);

    Transaction getTransaction(Long transactionId);

    List<Transaction> findAll();

    void deleteTransaction(Long accountId);

}
