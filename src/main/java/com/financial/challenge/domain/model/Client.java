package com.financial.challenge.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import com.financial.challenge.domain.util.enums.DocumentTypeEnum;

import lombok.Data;

@Data
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

  public boolean isOlder() {
    return calculateAge() >= 18;
  }

  public int calculateAge() {
    return Period.between(this.birthDate, LocalDate.now()).getYears();
  }
}
