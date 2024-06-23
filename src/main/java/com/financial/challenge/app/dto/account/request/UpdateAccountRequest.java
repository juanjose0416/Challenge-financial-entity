package com.financial.challenge.app.dto.account.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.challenge.domain.util.enums.StatusEnum;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountRequest {

  @NotNull(message = "status can't be null")
  @JsonProperty
  private StatusEnum status;
}
