package com.financial.challenge.infraestructure.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.financial.challenge.app.usecase.account.AccountUpdaterUseCase;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
@Valid
public class AccountController {

  private final AccountCreatorUseCase accountCreatorUseCase;
  private final AccountUpdaterUseCase accountUpdaterUseCase;

  @PostMapping(
      path = "",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request)
      throws Exception {
    AccountResponse accountResponse = accountCreatorUseCase.createAccount(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
  }

  @PutMapping(path = "/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateAccount(
      @PathVariable("accountId") Long accountId, @RequestBody UpdateAccountRequest request) {
    accountUpdaterUseCase.updateAccount(request, accountId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
