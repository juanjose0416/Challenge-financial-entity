package com.financial.challenge.app.validator;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate> {

  @Override
  public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
    return calculateAge(birthDate) >= 18;
  }

  private int calculateAge(LocalDate birthDate) {
    return Period.between(birthDate, LocalDate.now()).getYears();
  }
}
