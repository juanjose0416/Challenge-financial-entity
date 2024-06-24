package com.financial.challenge.app.usecase.account.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.CurrentAccount;
import com.financial.challenge.domain.service.AccountService;

class AccountDeleterUseCaseImplTest {

  @Mock private AccountService accountService;

  @InjectMocks private AccountDeleterUseCaseImpl accountDeleterUseCase;

  private static final Long ACCOUNT_ID = 1L;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testDeleteAccount() {
    when(accountService.getAccountById(ACCOUNT_ID)).thenReturn(createMockAccount());

    accountDeleterUseCase.deleteAccount(ACCOUNT_ID);

    verify(accountService).getAccountById(ACCOUNT_ID);
    verify(accountService).delete(ACCOUNT_ID);
  }

  private Account createMockAccount() {
    return CurrentAccount.builder().balance(BigDecimal.ZERO).build();
  }
}
