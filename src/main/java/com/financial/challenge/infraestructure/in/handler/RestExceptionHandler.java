package com.financial.challenge.infraestructure.in.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.financial.challenge.domain.exception.AccountException;
import com.financial.challenge.domain.exception.ClientException;
import com.financial.challenge.domain.exception.TransactionException;

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

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
