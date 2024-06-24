package com.financial.challenge.infraestructure.out.jpa.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.financial.challenge.domain.model.Transaction;
import com.financial.challenge.domain.spi.TransactionPersistencePort;
import com.financial.challenge.infraestructure.out.jpa.entity.TransactionEntity;
import com.financial.challenge.infraestructure.out.jpa.mapper.JPATransactionMapper;
import com.financial.challenge.infraestructure.out.jpa.mapper.impl.TransactionBuilderImpl;
import com.financial.challenge.infraestructure.out.jpa.repository.TransactionRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Transactional
public class TransactionPersistenceAdapter implements TransactionPersistencePort {

  private final TransactionRepository transactionRepository;
  private final JPATransactionMapper JPATransactionMapper;

  @Override
  public Transaction save(Transaction transaction) {
    TransactionEntity transactionEntity = JPATransactionMapper.toTransactionEntity(transaction);
    TransactionEntity transactionEntityCreated = transactionRepository.save(transactionEntity);
    return TransactionBuilderImpl.toModel(transactionEntityCreated);
  }

  @Override
  public Optional<Transaction> findTransactionById(Long transactionId) {
    Optional<TransactionEntity> transactionEntity = transactionRepository.findById(transactionId);
    return transactionEntity.map(TransactionBuilderImpl::toModel);
  }

  @Override
  public List<Transaction> findAll() {
    return TransactionBuilderImpl.toModelList(transactionRepository.findAll());
  }

  @Override
  public void deleteById(Long transactionId) {
    transactionRepository.deleteById(transactionId);
  }
}
