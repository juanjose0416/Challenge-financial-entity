package com.financial.challenge.app.usecase.account.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.financial.challenge.app.dto.account.request.CreateAccountRequest;
import com.financial.challenge.app.dto.account.response.AccountResponse;
import com.financial.challenge.app.pattern.factory.AccountFactory;
import com.financial.challenge.app.mapper.AccountResponseMapper;
import com.financial.challenge.app.usecase.account.AccountCreatorUseCase;
import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.service.AccountService;
import com.financial.challenge.domain.service.ClientService;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountCreatorUseCaseImpl implements AccountCreatorUseCase {

  private final AccountService accountService;
  private final ClientService clientService;
  private final ApplicationContext applicationContext;
  private final AccountResponseMapper accountResponseMapper;

  private static final String CURRENT_ACCOUNT_FACTORY = "CurrentAccountFactory";
  private static final String SAVING_ACCOUNT_FACTORY = "SavingAccountFactory";

  @Override
  public AccountResponse createAccount(CreateAccountRequest request) throws Exception {
    Client client = clientService.getClientById(request.getClientId());
    Account account = initAccount(request, client);
    return accountResponseMapper.accountToAccountResponse(accountService.save(account));
  }

  private Account initAccount(CreateAccountRequest request, Client client) {
    if (AccountTypeEnum.CURRENT_ACCOUNT.equals(request.getAccountType())) {
      return getAccountFactory(CURRENT_ACCOUNT_FACTORY).create(request.getIsGMF(), client);
    } else {
      return getAccountFactory(SAVING_ACCOUNT_FACTORY).create(request.getIsGMF(), client);
    }
  }

  private AccountFactory getAccountFactory(String accountFactory) {
    return (AccountFactory) applicationContext.getBean(accountFactory);
  }
}
