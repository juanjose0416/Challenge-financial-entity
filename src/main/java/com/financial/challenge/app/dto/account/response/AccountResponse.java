package com.financial.challenge.app.dto.account.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;
import com.financial.challenge.domain.util.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    @JsonProperty
    private AccountTypeEnum accountType;

    @JsonProperty
    private String accountNumber;

    @JsonProperty
    private StatusEnum status;

    @JsonProperty
    private BigDecimal balance;

    @JsonProperty
    private boolean isGMF;

    @JsonProperty
    private LocalDateTime createdAt;
}
