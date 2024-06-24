package com.financial.challenge.app.pattern.strategy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.model.CurrentAccount;
import com.financial.challenge.domain.model.SavingAccount;
import com.financial.challenge.domain.pattern.strategy.TransactionStrategy;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;

class TransactionContextTest {

  private TransactionContext transactionContext;
  private TransactionStrategy transactionStrategyMock;

  @BeforeEach
  public void setup() {
    transactionContext = new TransactionContext();
    transactionStrategyMock = mock(TransactionStrategy.class);
    transactionContext.setTransactionStrategy(transactionStrategyMock);
  }

  @Test
  public void testExecuteStrategy() {
    Account sourceAccount =
        CurrentAccount.builder()
            .id(1L)
            .accountType(AccountTypeEnum.CURRENT_ACCOUNT)
            .accountNumber("330001")
            .status(StatusEnum.ACTIVE)
            .balance(BigDecimal.valueOf(1000))
            .isGMFFree(true)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .client(new Client())
            .build();

    Account targetAccount =
        SavingAccount.builder()
            .id(2L)
            .accountType(AccountTypeEnum.SAVING_ACCOUNT)
            .accountNumber("530002")
            .status(StatusEnum.ACTIVE)
            .balance(BigDecimal.valueOf(500))
            .isGMFFree(false)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .client(new Client())
            .build();

    BigDecimal amount = BigDecimal.valueOf(200);
    transactionContext.executeStrategy(sourceAccount, amount, targetAccount);
    verify(transactionStrategyMock).execute(sourceAccount, amount, targetAccount);
  }
}
