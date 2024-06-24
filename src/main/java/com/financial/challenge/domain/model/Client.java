package com.financial.challenge.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.financial.challenge.domain.util.enums.DocumentTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
  private Long id;
  private DocumentTypeEnum documentType;
  private String documentNumber;
  private String name;
  private String lastName;
  private String email;
  private LocalDate birthDate;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private List<Account> accounts = new ArrayList<>();
}
