package com.financial.challenge.infraestructure.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.challenge.app.dto.client.request.CreateClientRequest;
import com.financial.challenge.app.dto.client.response.ClientResponse;
import com.financial.challenge.app.usecase.ClientCreatorUseCase;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@Valid
public class ClientController {

    private final ClientCreatorUseCase clientCreatorUseCase;

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponse> createClient(@RequestBody CreateClientRequest request){
        ClientResponse clientResponse = clientCreatorUseCase.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
    }
}
