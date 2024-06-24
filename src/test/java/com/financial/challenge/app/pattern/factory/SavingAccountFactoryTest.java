package com.financial.challenge.app.pattern.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.model.SavingAccount;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;
import com.financial.challenge.domain.util.generator.AccountNumberGenerator;

class SavingAccountFactoryTest {

  private AccountFactory savingAccountFactory;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    savingAccountFactory = new SavingAccountFactory();
  }

  @Test
  public void testCreateSavingAccount() {
    Client mockClient = mock(Client.class);

    String generatedAccountNumber = "987654321";
    mockStatic(AccountNumberGenerator.class);
    when(AccountNumberGenerator.generateNumber()).thenReturn(generatedAccountNumber);

    Account createdAccount = savingAccountFactory.create(true, mockClient);

    assertEquals(AccountTypeEnum.SAVING_ACCOUNT, createdAccount.getAccountType());
    assertEquals("53" + generatedAccountNumber, createdAccount.getAccountNumber());
    assertEquals(StatusEnum.ACTIVE, createdAccount.getStatus());
    assertEquals(BigDecimal.ZERO, createdAccount.getBalance());
    assertEquals(true, ((SavingAccount) createdAccount).isGMFFree());
    assertEquals(LocalDateTime.now().getDayOfYear(), createdAccount.getCreatedAt().getDayOfYear());
    assertEquals(mockClient, createdAccount.getClient());
  }
}
