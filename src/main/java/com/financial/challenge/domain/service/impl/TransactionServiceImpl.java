package com.financial.challenge.domain.service.impl;

import org.springframework.stereotype.Service;

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

}
