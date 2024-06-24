package com.financial.challenge.app.dto.account.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.challenge.domain.util.enums.AccountTypeEnum;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountRequest {

  @NotNull(message = "accountType can't be null")
  @JsonProperty
  private AccountTypeEnum accountType;

  @NotNull(message = "GMF can't be null")
  @JsonProperty
  private Boolean isGMF;

  @NotNull(message = "clientId can't be null")
  @JsonProperty
  private Long clientId;
}
