package com.financial.challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.financial.challenge.domain.exception.AccountException;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Account {

  protected Long id;
  protected AccountTypeEnum accountType;
  protected String accountNumber;
  protected StatusEnum status;
  protected BigDecimal balance;
  protected boolean isGMFFree;
  protected LocalDateTime createdAt;
  protected LocalDateTime updatedAt;
  protected Client client;

  public void activate() {
    this.status = StatusEnum.ACTIVE;
    this.updatedAt = LocalDateTime.now();
  }

  public void inactivate() {
    this.status = StatusEnum.INACTIVE;
    this.updatedAt = LocalDateTime.now();
  }

  public void cancel() throws AccountException {
    if (this.balance.compareTo(BigDecimal.ZERO) != 0) {
      throw new AccountException("Current account can only be cancelled with zero balance");
    }
    this.status = StatusEnum.CANCELLED;
    this.updatedAt = LocalDateTime.now();
  }

  public void deposit(BigDecimal amount) {
    this.balance = this.balance.add(amount);
    this.updatedAt = LocalDateTime.now();
  }

  public abstract void withdraw(BigDecimal amount) throws AccountException ;

  public void transfer(Account targetAccount, BigDecimal amount) {
    BigDecimal gmf = calculateGMF(amount);
    this.withdraw(amount.add(gmf));
    targetAccount.deposit(amount);
  }

  public BigDecimal calculateGMF(BigDecimal amount) {
    if (!this.isGMFFree()) {
      return amount.multiply(BigDecimal.valueOf(0.004));
    } else {
      return BigDecimal.ZERO;
    }
  }
}
