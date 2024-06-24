package com.financial.challenge.infraestructure.out.jpa.mapper;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Transaction;
import com.financial.challenge.infraestructure.out.jpa.entity.TransactionEntity;

public interface TransactionBuilder {

    Transaction toModel(TransactionEntity transactionEntity);
}
