package com.financial.challenge.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.financial.challenge.domain.service.AccountService;
import com.financial.challenge.domain.service.ClientService;
import com.financial.challenge.domain.service.TransactionService;
import com.financial.challenge.domain.service.impl.AccountServiceImpl;
import com.financial.challenge.domain.service.impl.ClientServiceImpl;
import com.financial.challenge.domain.service.impl.TransactionServiceImpl;
import com.financial.challenge.domain.spi.AccountPersistencePort;
import com.financial.challenge.domain.spi.ClientPersistencePort;
import com.financial.challenge.domain.spi.TransactionPersistencePort;
import com.financial.challenge.infraestructure.out.jpa.adapter.AccountPersistenceAdapter;
import com.financial.challenge.infraestructure.out.jpa.adapter.ClientPersistenceAdapter;
import com.financial.challenge.infraestructure.out.jpa.adapter.TransactionPersistenceAdapter;
import com.financial.challenge.infraestructure.out.jpa.mapper.AccountMapper;
import com.financial.challenge.infraestructure.out.jpa.mapper.ClientMapper;
import com.financial.challenge.infraestructure.out.jpa.mapper.JPATransactionMapper;
import com.financial.challenge.infraestructure.out.jpa.repository.AccountRepository;
import com.financial.challenge.infraestructure.out.jpa.repository.ClientRepository;
import com.financial.challenge.infraestructure.out.jpa.repository.TransactionRepository;

@Configuration
public class BeanConfiguration {

  private final AccountMapper accountMapper;
  private final ClientMapper clientMapper;
  private final JPATransactionMapper JPATransactionMapper;
  private final AccountRepository accountRepository;
  private final ClientRepository clientRepository;
  private final TransactionRepository transactionRepository;

  public BeanConfiguration(
      AccountMapper accountMapper,
      ClientMapper clientMapper,
      JPATransactionMapper JPATransactionMapper,
      AccountRepository accountRepository,
      ClientRepository clientRepository,
      TransactionRepository transactionRepository) {
    this.accountMapper = accountMapper;
    this.clientMapper = clientMapper;
    this.JPATransactionMapper = JPATransactionMapper;
    this.accountRepository = accountRepository;
    this.clientRepository = clientRepository;
    this.transactionRepository = transactionRepository;
  }

  @Bean
  public AccountPersistencePort accountPersistencePort() {
    return new AccountPersistenceAdapter(accountRepository, accountMapper);
  }

  @Bean
  public AccountService accountService() {
    return new AccountServiceImpl(accountPersistencePort());
  }

  @Bean
  public ClientPersistencePort clientPersistencePort() {
    return new ClientPersistenceAdapter(clientRepository, clientMapper);
  }

  @Bean
  public ClientService clientService() {
    return new ClientServiceImpl(clientPersistencePort());
  }

  @Bean
  public TransactionPersistencePort transactionPersistencePort() {
    return new TransactionPersistenceAdapter(transactionRepository, JPATransactionMapper);
  }

  @Bean
  public TransactionService transactionService() {
    return new TransactionServiceImpl(transactionPersistencePort());
  }
}
