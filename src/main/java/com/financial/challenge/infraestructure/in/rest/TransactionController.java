package com.financial.challenge.infraestructure.in.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.challenge.app.dto.transaction.request.DepositRequest;
import com.financial.challenge.app.dto.transaction.request.TransactionRequest;
import com.financial.challenge.app.dto.transaction.response.TransactionResponse;
import com.financial.challenge.app.usecase.transaction.TransactionDeleterUseCase;
import com.financial.challenge.app.usecase.transaction.TransactionGetterUseCase;
import com.financial.challenge.app.usecase.transaction.TransactionUseCase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
@Valid
public class TransactionController {

  private final TransactionUseCase transactionUseCase;
  private final TransactionGetterUseCase transactionGetterUseCase;
  private final TransactionDeleterUseCase transactionDeleterUseCase;

  @PostMapping(
      value = "/deposit",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TransactionResponse> deposit(@Valid @RequestBody DepositRequest request)
      throws Exception {
    TransactionResponse response = transactionUseCase.deposit(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping(
      value = "/withdraw",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TransactionResponse> withdraw(@Valid @RequestBody DepositRequest request) throws Exception {
    TransactionResponse response = transactionUseCase.withdraw(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping(
      value = "/transfer",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TransactionResponse> transfer(
      @Valid @RequestBody TransactionRequest request) throws Exception {
    TransactionResponse response = transactionUseCase.transfer(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TransactionResponse> getTransaction(@PathVariable("transactionId") @NotNull Long transactionId){
    TransactionResponse response = transactionGetterUseCase.getTransaction(transactionId);
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TransactionResponse>> findAll(){
    List<TransactionResponse> transactionResponses = transactionGetterUseCase.findAll();
    return ResponseEntity.ok(transactionResponses);
  }

  @DeleteMapping(value = "/{transactionId}")
  public ResponseEntity<Void> deleteTransaction(@PathVariable("transactionId") @NotNull Long transactionId){
    transactionDeleterUseCase.deleteTransaction(transactionId);
    return ResponseEntity.ok().build();
  }
}
