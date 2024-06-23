package com.financial.challenge.domain.spi;

import com.financial.challenge.domain.model.Transaction;

public interface TransactionPersistencePort {

    Transaction save(Transaction transaction);

}
