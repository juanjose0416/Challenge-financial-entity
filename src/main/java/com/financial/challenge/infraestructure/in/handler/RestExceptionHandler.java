package com.financial.challenge.infraestructure.in.handler;

import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.financial.challenge.domain.exception.AccountException;
import com.financial.challenge.domain.exception.ClientException;
import com.financial.challenge.domain.exception.TransactionException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ClientException.class)
  protected ResponseEntity<Object> handleClientError(ClientException ex) {
    return buildResponseEntity(new ApiError(HttpStatus.FORBIDDEN, ex.getMessage(), ex));
  }

  @ExceptionHandler(AccountException.class)
  protected ResponseEntity<Object> handleSubjectError(AccountException ex) {
    return buildResponseEntity(new ApiError(HttpStatus.FORBIDDEN, ex.getMessage(), ex));
  }

  @ExceptionHandler(TransactionException.class)
  protected ResponseEntity<Object> handleGradeError(TransactionException ex) {
    return buildResponseEntity(new ApiError(HttpStatus.FORBIDDEN, ex.getMessage(), ex));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException ex) {
    logger.error("Constraint violation exception", ex);
    if (CollectionUtils.isEmpty(ex.getConstraintViolations())) {
      return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "", ex.getMessage(), ex));
    }
    String errorMessage =
        ex.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining());
    return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "", errorMessage, ex));
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
