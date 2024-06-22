package com.financial.challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.financial.challenge.domain.util.AccountTypeEnum;
import com.financial.challenge.domain.util.StatusEnum;

import lombok.Data;

@Data
public class Account {

    private Long id;
    private AccountTypeEnum accountType;
    private String accountNumber;
    private StatusEnum status;
    private BigDecimal balance;
    private boolean isGMF;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Client client;
}
