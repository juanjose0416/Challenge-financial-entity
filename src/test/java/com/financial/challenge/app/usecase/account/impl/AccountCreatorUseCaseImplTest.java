package com.financial.challenge.app.usecase.account.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import com.financial.challenge.app.dto.account.request.CreateAccountRequest;
import com.financial.challenge.app.dto.account.response.AccountResponse;
import com.financial.challenge.app.mapper.AccountResponseMapper;
import com.financial.challenge.app.pattern.factory.AccountFactory;
import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.model.CurrentAccount;
import com.financial.challenge.domain.model.SavingAccount;
import com.financial.challenge.domain.service.AccountService;
import com.financial.challenge.domain.service.ClientService;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;

class AccountCreatorUseCaseImplTest {
  @Mock private AccountService accountService;

  @Mock private ClientService clientService;

  @Mock private ApplicationContext applicationContext;

  @Mock private AccountResponseMapper accountResponseMapper;

  @Mock(name = "CurrentAccountFactory")
  private AccountFactory currentAccountFactory;

  @Mock(name = "SavingAccountFactory")
  private AccountFactory savingAccountFactory;

  @InjectMocks private AccountCreatorUseCaseImpl accountCreatorUseCase;

  private static final Long CLIENT_ID = 1L;
  private static final Boolean IS_GMF = true;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateCurrentAccount() {
    CreateAccountRequest request = new CreateAccountRequest();
    request.setClientId(CLIENT_ID);
    request.setAccountType(AccountTypeEnum.CURRENT_ACCOUNT);
    request.setIsGMF(IS_GMF);

    Client mockClient = new Client();
    when(clientService.getClientById(CLIENT_ID)).thenReturn(mockClient);

    Account mockAccount = CurrentAccount.builder().build();
    when(currentAccountFactory.create(IS_GMF, mockClient)).thenReturn(mockAccount);
    when(accountService.save(mockAccount)).thenReturn(mockAccount);

    AccountResponse mockResponse = new AccountResponse();
    when(accountResponseMapper.accountToAccountResponse(mockAccount)).thenReturn(mockResponse);
    when(applicationContext.getBean(anyString())).thenReturn(currentAccountFactory);
    AccountResponse response = accountCreatorUseCase.createAccount(request);

    assertEquals(mockResponse, response);
  }

  @Test
  void testCreateSavingAccount() {
    CreateAccountRequest request = new CreateAccountRequest();
    request.setClientId(CLIENT_ID);
    request.setAccountType(AccountTypeEnum.SAVING_ACCOUNT);
    request.setIsGMF(IS_GMF);

    Client mockClient = new Client();
    when(clientService.getClientById(CLIENT_ID)).thenReturn(mockClient);

    Account mockAccount = SavingAccount.builder().balance(BigDecimal.ZERO).build();
    when(savingAccountFactory.create(IS_GMF, mockClient)).thenReturn(mockAccount);
    when(accountService.save(mockAccount)).thenReturn(mockAccount);

    AccountResponse mockResponse = new AccountResponse();
    when(accountResponseMapper.accountToAccountResponse(mockAccount)).thenReturn(mockResponse);

    when(applicationContext.getBean(anyString())).thenReturn(savingAccountFactory);
    AccountResponse response = accountCreatorUseCase.createAccount(request);

    assertEquals(mockResponse, response);
  }
}
