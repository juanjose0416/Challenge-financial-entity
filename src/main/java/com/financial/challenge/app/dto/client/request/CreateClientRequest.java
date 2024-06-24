package com.financial.challenge.app.dto.client.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.challenge.app.validator.ValidAge;
import com.financial.challenge.domain.util.enums.DocumentTypeEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientRequest {

  @NotNull(message = "documentType can't be null")
  @JsonProperty
  private DocumentTypeEnum documentType;

  @NotBlank(message = "documentNumber can't be null")
  @JsonProperty
  private String documentNumber;

  @NotBlank(message = "name can't be null")
  @JsonProperty
  private String name;

  @NotBlank(message = "lastName can't be null")
  @JsonProperty
  private String lastName;

  @Email(message = "Email must be valid")
  @NotBlank(message = "email can't be null")
  @JsonProperty
  private String email;

  @ValidAge
  @NotNull(message = "birthDate can't be null")
  @JsonProperty
  private LocalDate birthDate;
}
