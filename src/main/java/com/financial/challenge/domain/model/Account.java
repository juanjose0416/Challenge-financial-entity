package com.financial.challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {

  protected Long id;
  protected AccountTypeEnum accountType;
  protected String accountNumber;
  protected StatusEnum status;
  protected BigDecimal balance;
  protected boolean isGMF;
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

  public void cancel() throws Exception {
    if (this.balance.compareTo(BigDecimal.ZERO) != 0) {
      throw new Exception("Current account can only be cancelled with zero balance");
    }
    this.status = StatusEnum.CANCELLED;
    this.updatedAt = LocalDateTime.now();
  }
}
