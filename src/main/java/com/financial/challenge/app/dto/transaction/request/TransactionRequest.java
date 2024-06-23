package com.financial.challenge.app.dto.transaction.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @JsonProperty
    @Positive
    @NotNull(message = "amount can't be null")
    private BigDecimal amount;

    @JsonProperty
    @NotBlank(message = "originAccount can't be null")
    private String originAccount;

    @JsonProperty
    @NotBlank(message = "destinationAccount can't be null")
    private String destinationAccount;
}
