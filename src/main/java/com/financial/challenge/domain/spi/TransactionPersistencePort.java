package com.financial.challenge.domain.spi;

import java.util.List;
import java.util.Optional;

import com.financial.challenge.domain.model.Transaction;

public interface TransactionPersistencePort {

    Transaction save(Transaction transaction);

    Optional<Transaction> findTransactionById(Long transactionId);

    List<Transaction> findAll();

    void deleteById(Long transactionId);

}
