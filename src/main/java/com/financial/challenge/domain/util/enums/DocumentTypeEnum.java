package com.financial.challenge.domain.util.enums;

public enum DocumentTypeEnum {
  CC("Cedula"),
  NIT("NIT"),
  PP("Passport");

  private String description;

  DocumentTypeEnum(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
