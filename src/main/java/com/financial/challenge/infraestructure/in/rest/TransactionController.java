package com.financial.challenge.infraestructure.in.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.challenge.app.dto.transaction.request.DepositRequest;
import com.financial.challenge.app.dto.transaction.request.TransactionRequest;
import com.financial.challenge.app.dto.transaction.response.TransactionResponse;
import com.financial.challenge.app.usecase.transaction.TransactionUseCase;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
@Valid
public class TransactionController {

  private final TransactionUseCase transactionUseCase;

  @PostMapping(
      value = "/deposit",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TransactionResponse> deposit(@Validated @RequestBody DepositRequest request)
      throws Exception {
    TransactionResponse response = transactionUseCase.deposit(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping(
      value = "/withdraw",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TransactionResponse> withdraw(
      @Validated @RequestBody DepositRequest request) throws Exception {
    TransactionResponse response = transactionUseCase.withdraw(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping(
      value = "/transfer",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TransactionResponse> transfer(
      @Validated @RequestBody TransactionRequest request) throws Exception {
    TransactionResponse response = transactionUseCase.transfer(request);
    return ResponseEntity.ok(response);
  }
}
