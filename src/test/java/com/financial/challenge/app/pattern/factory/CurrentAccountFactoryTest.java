package com.financial.challenge.app.pattern.factory;

import static org.junit.jupiter.api.Assertions.*;
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
import com.financial.challenge.domain.model.CurrentAccount;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;
import com.financial.challenge.domain.util.generator.AccountNumberGenerator;

class CurrentAccountFactoryTest {

    private AccountFactory currentAccountFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        currentAccountFactory = new CurrentAccountFactory();
    }

    @Test
    public void testCreateCurrentAccount() {
        Client mockClient = mock(Client.class);
        String generatedAccountNumber = "123456789";
        mockStatic(AccountNumberGenerator.class);
        when(AccountNumberGenerator.generateNumber()).thenReturn(generatedAccountNumber);
        Account createdAccount = currentAccountFactory.create(false, mockClient);
        assertEquals(AccountTypeEnum.CURRENT_ACCOUNT, createdAccount.getAccountType());
        assertEquals("33" + generatedAccountNumber, createdAccount.getAccountNumber());
        assertEquals(StatusEnum.INACTIVE, createdAccount.getStatus());
        assertEquals(BigDecimal.ZERO, createdAccount.getBalance());
        assertEquals(false, ((CurrentAccount) createdAccount).isGMFFree());
        assertEquals(LocalDateTime.now().getDayOfYear(), createdAccount.getCreatedAt().getDayOfYear());
        assertEquals(mockClient, createdAccount.getClient());
    }

}