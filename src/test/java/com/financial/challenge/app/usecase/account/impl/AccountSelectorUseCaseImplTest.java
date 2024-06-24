package com.financial.challenge.app.usecase.account.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.financial.challenge.app.dto.account.response.AccountResponse;
import com.financial.challenge.app.mapper.AccountResponseMapper;
import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.model.CurrentAccount;
import com.financial.challenge.domain.model.SavingAccount;
import com.financial.challenge.domain.service.AccountService;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;

class AccountSelectorUseCaseImplTest {

  @Mock private AccountService accountService;

  @Mock private AccountResponseMapper accountResponseMapper;

  @InjectMocks private AccountSelectorUseCaseImpl accountSelectorUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAccount() {
    Long accountId = 1L;
    Account account =
        CurrentAccount.builder()
            .id(accountId)
            .accountType(AccountTypeEnum.CURRENT_ACCOUNT)
            .accountNumber("123456")
            .status(StatusEnum.ACTIVE)
            .balance(BigDecimal.valueOf(1000))
            .isGMFFree(false)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .client(new Client())
            .build();

    AccountResponse expectedResponse = new AccountResponse();

    when(accountService.getAccountById(accountId)).thenReturn(account);
    when(accountResponseMapper.accountToAccountResponse(account)).thenReturn(expectedResponse);

    AccountResponse actualResponse = accountSelectorUseCase.getAccount(accountId);

    assertEquals(expectedResponse, actualResponse);
    verify(accountService, times(1)).getAccountById(accountId);
    verify(accountResponseMapper, times(1)).accountToAccountResponse(account);
  }

  @Test
  void testGetAll() {
    List<Account> accounts =
        Arrays.asList(
            CurrentAccount.builder()
                .id(1L)
                .accountType(AccountTypeEnum.CURRENT_ACCOUNT)
                .accountNumber("123456")
                .status(StatusEnum.ACTIVE)
                .balance(BigDecimal.valueOf(1000))
                .isGMFFree(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .client(new Client())
                .build(),
            SavingAccount.builder()
                .id(2L)
                .accountType(AccountTypeEnum.SAVING_ACCOUNT)
                .accountNumber("789012")
                .status(StatusEnum.ACTIVE)
                .balance(BigDecimal.valueOf(500))
                .isGMFFree(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .client(new Client())
                .build());

    List<AccountResponse> expectedResponses =
        Arrays.asList(new AccountResponse(), new AccountResponse());

    when(accountService.getAll()).thenReturn(accounts);
    when(accountResponseMapper.accountsToAccountResponses(accounts)).thenReturn(expectedResponses);

    List<AccountResponse> actualResponses = accountSelectorUseCase.getAll();
    assertEquals(expectedResponses.size(), actualResponses.size());
    assertEquals(expectedResponses.get(0), actualResponses.get(0));
    assertEquals(expectedResponses.get(1), actualResponses.get(1));
    verify(accountService, times(1)).getAll();
    verify(accountResponseMapper, times(1)).accountsToAccountResponses(accounts);
  }
}
