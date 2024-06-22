package com.financial.challenge.domain.util.enums;

public enum AccountTypeEnum {
  CURRENT_ACCOUNT("Cuenta corriente"),
  SAVING_ACCOUNT("Cuenta de ahorros");

  private String description;

  AccountTypeEnum(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
