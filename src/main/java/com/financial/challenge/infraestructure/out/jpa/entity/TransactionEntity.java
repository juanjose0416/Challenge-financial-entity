package com.financial.challenge.infraestructure.out.jpa.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.financial.challenge.domain.util.enums.TransactionStatusEnum;
import com.financial.challenge.domain.util.enums.TransactionTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "transaction_type", nullable = false)
  private TransactionTypeEnum transactionType;

  @Column(name = "amount", precision = 10, scale = 2, nullable = false)
  private BigDecimal amount;

  @Column(name = "gmf", precision = 10, scale = 2, nullable = false)
  private BigDecimal gmf;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "origin_account_id")
  private AccountEntity originAccount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "destination_account_id")
  private AccountEntity destinationAccount;

  @Enumerated(EnumType.STRING)
  @Column(name = "transaction_type", nullable = false)
  private TransactionStatusEnum status;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TransactionTypeEnum getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getGmf() {
    return gmf;
  }

  public void setGmf(BigDecimal gmf) {
    this.gmf = gmf;
  }

  public TransactionStatusEnum getStatus() {
    return status;
  }

  public void setStatus(TransactionStatusEnum status) {
    this.status = status;
  }

  public AccountEntity getOriginAccount() {
    return originAccount;
  }

  public void setOriginAccount(AccountEntity originAccount) {
    this.originAccount = originAccount;
  }

  public AccountEntity getDestinationAccount() {
    return destinationAccount;
  }

  public void setDestinationAccount(AccountEntity destinationAccount) {
    this.destinationAccount = destinationAccount;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
