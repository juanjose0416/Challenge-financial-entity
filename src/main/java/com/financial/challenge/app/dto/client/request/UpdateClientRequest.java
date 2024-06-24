package com.financial.challenge.app.dto.client.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.challenge.app.validator.ValidAge;
import com.financial.challenge.domain.util.enums.DocumentTypeEnum;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateClientRequest {

  @JsonProperty private DocumentTypeEnum documentType;

  @JsonProperty private String documentNumber;

  @JsonProperty private String name;

  @JsonProperty private String lastName;

  @Email(message = "Email must be valid")
  @JsonProperty
  private String email;

  @ValidAge @JsonProperty private LocalDate birthDate;
}
