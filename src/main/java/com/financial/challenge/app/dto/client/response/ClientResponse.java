package com.financial.challenge.app.dto.client.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.util.enums.DocumentTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

  @JsonProperty private DocumentTypeEnum documentType;

  @JsonProperty private String documentNumber;

  @JsonProperty private String name;

  @JsonProperty private String lastName;

  @JsonProperty private String email;

  @JsonProperty private LocalDate birthDate;

  @JsonProperty private LocalDateTime createdAt;

  @JsonProperty private List<Account> accounts;
}
