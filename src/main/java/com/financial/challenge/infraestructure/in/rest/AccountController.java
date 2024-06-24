package com.financial.challenge.infraestructure.in.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.challenge.app.dto.account.request.CreateAccountRequest;
import com.financial.challenge.app.dto.account.request.UpdateAccountRequest;
import com.financial.challenge.app.dto.account.response.AccountResponse;
import com.financial.challenge.app.usecase.account.AccountCreatorUseCase;
import com.financial.challenge.app.usecase.account.AccountDeleterUseCase;
import com.financial.challenge.app.usecase.account.AccountSelectorUseCase;
import com.financial.challenge.app.usecase.account.AccountUpdaterUseCase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
@Validated
public class AccountController {

  private final AccountCreatorUseCase accountCreatorUseCase;
  private final AccountUpdaterUseCase accountUpdaterUseCase;
  private final AccountDeleterUseCase accountDeleterUseCase;
  private final AccountSelectorUseCase accountSelectorUseCase;

  @PostMapping(
      path = "",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AccountResponse> createAccount(
      @Valid @RequestBody CreateAccountRequest request) {
    AccountResponse accountResponse = accountCreatorUseCase.createAccount(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
  }

  @PutMapping(path = "/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateAccount(
      @PathVariable("accountId") @NotNull Long accountId,
      @Valid @RequestBody UpdateAccountRequest request) {
    accountUpdaterUseCase.updateAccount(request, accountId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping(path = "/{accountId}")
  public ResponseEntity<Void> deleteAccount(@PathVariable("accountId") @NotNull Long accountId) {
    accountDeleterUseCase.deleteAccount(accountId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping(path = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AccountResponse> getAccount(
      @PathVariable("accountId") @NotNull Long accountId) {
    AccountResponse accountResponse = accountSelectorUseCase.getAccount(accountId);
    return ResponseEntity.ok(accountResponse);
  }

  @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AccountResponse>> getAll() {
    List<AccountResponse> accountResponse = accountSelectorUseCase.getAll();
    return ResponseEntity.ok(accountResponse);
  }
}
