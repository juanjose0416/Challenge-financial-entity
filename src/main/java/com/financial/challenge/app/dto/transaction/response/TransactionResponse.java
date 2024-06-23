package com.financial.challenge.app.dto.transaction.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.challenge.domain.util.enums.TransactionStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

  @JsonProperty private Long id;

  @JsonProperty private String transactionType;

  @JsonProperty private Long sourceAccountId;

  @JsonProperty private BigDecimal amount;

  @JsonProperty private BigDecimal gmf;

  @JsonProperty private TransactionStatusEnum transactionStatus;

  @JsonProperty private LocalDateTime transactionDate;

}
