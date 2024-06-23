package com.financial.challenge.infraestructure.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.challenge.app.dto.account.request.CreateAccountRequest;
import com.financial.challenge.app.dto.account.response.AccountResponse;
import com.financial.challenge.app.usecase.account.AccountCreatorUseCase;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
@Valid
public class AccountController {

    private final AccountCreatorUseCase accountCreatorUseCase;

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request){
        AccountResponse accountResponse = accountCreatorUseCase.createAccount();
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
    }
}
