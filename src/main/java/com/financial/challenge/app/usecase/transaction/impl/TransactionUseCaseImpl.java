package com.financial.challenge.app.usecase.transaction.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.financial.challenge.app.dto.transaction.request.DepositRequest;
import com.financial.challenge.app.dto.transaction.request.TransactionRequest;
import com.financial.challenge.app.dto.transaction.response.TransactionResponse;
import com.financial.challenge.app.mapper.AppTransactionResponseMapper;
import com.financial.challenge.app.pattern.builder.TransactionBuilder;
import com.financial.challenge.app.pattern.strategy.TransactionContext;
import com.financial.challenge.app.usecase.transaction.TransactionUseCase;
import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Transaction;
import com.financial.challenge.domain.pattern.strategy.DepositStrategy;
import com.financial.challenge.domain.pattern.strategy.TransferStrategy;
import com.financial.challenge.domain.pattern.strategy.WithDrawStrategy;
import com.financial.challenge.domain.service.AccountService;
import com.financial.challenge.domain.service.TransactionService;
import com.financial.challenge.domain.util.enums.TransactionStatusEnum;
import com.financial.challenge.domain.util.enums.TransactionTypeEnum;

import lombok.AllArgsConstructor;

@Service
@Transactional
public class TransactionUseCaseImpl implements TransactionUseCase {

  private final AccountService accountService;
  private final TransactionService transactionService;
  private final TransactionContext transactionContext;
  private final AppTransactionResponseMapper appTransactionResponseMapper;

  public TransactionUseCaseImpl(
      AccountService accountService,
      TransactionService transactionService,
      AppTransactionResponseMapper appTransactionResponseMapper) {
    this.accountService = accountService;
    this.transactionService = transactionService;
    this.appTransactionResponseMapper = appTransactionResponseMapper;
    this.transactionContext = new TransactionContext();
  }

  @Override
  public TransactionResponse deposit(DepositRequest depositRequest) {
    Account account = accountService.getAccount(depositRequest.getDestinationAccount());
    transactionContext.setTransactionStrategy(new DepositStrategy());
    transactionContext.executeStrategy(account, depositRequest.getAmount(), null);
    accountService.save(account);
    Transaction.TransactionBuilder transaction =
        TransactionBuilder.buildTransaction(
            depositRequest.getAmount(), BigDecimal.ZERO, null, account);
    return saveTransaction(
        TransactionTypeEnum.DEPOSIT, TransactionStatusEnum.SUCCESSFUL, transaction);
  }

  @Override
  public TransactionResponse withdraw(DepositRequest request) {
    Account account = accountService.getAccount(request.getDestinationAccount());
    Transaction.TransactionBuilder transaction =
        TransactionBuilder.buildTransaction(request.getAmount(), BigDecimal.ZERO, null, account);
    try {
      transactionContext.setTransactionStrategy(new WithDrawStrategy());
      transactionContext.executeStrategy(account, request.getAmount(), null);
      accountService.save(account);
      return saveTransaction(
          TransactionTypeEnum.WITHDRAWAL, TransactionStatusEnum.SUCCESSFUL, transaction);
    } catch (Exception e) {
      return saveTransaction(
          TransactionTypeEnum.WITHDRAWAL, TransactionStatusEnum.FAILED, transaction);
    }
  }

  @Override
  public TransactionResponse transfer(TransactionRequest request) {
    Account originAccount = accountService.getAccount(request.getOriginAccount());
    Account destinationAccount = accountService.getAccount(request.getDestinationAccount());
    BigDecimal gmf = originAccount.calculateGMF(request.getAmount());
    Transaction.TransactionBuilder transaction =
        TransactionBuilder.buildTransaction(
            request.getAmount(), gmf, originAccount, destinationAccount);
    try {
      transactionContext.setTransactionStrategy(new TransferStrategy());
      transactionContext.executeStrategy(originAccount, request.getAmount(), destinationAccount);
      accountService.save(originAccount);
      accountService.save(destinationAccount);
      return saveTransaction(
          TransactionTypeEnum.TRANSFER, TransactionStatusEnum.SUCCESSFUL, transaction);
    } catch (Exception e) {
      return saveTransaction(
          TransactionTypeEnum.TRANSFER, TransactionStatusEnum.FAILED, transaction);
    }
  }

  private TransactionResponse saveTransaction(
      TransactionTypeEnum transactionType,
      TransactionStatusEnum status,
      Transaction.TransactionBuilder transactionBuilder) {
    Transaction transaction =
        transactionBuilder.transactionType(transactionType).status(status).build();
    Transaction transactionResponse = transactionService.execute(transaction);
    return appTransactionResponseMapper.toTransactionResponse(transactionResponse);
  }
}
