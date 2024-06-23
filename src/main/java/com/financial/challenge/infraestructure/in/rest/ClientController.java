package com.financial.challenge.infraestructure.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.challenge.app.dto.client.request.CreateClientRequest;
import com.financial.challenge.app.dto.client.request.UpdateClientRequest;
import com.financial.challenge.app.dto.client.response.ClientResponse;
import com.financial.challenge.app.usecase.client.ClientCreatorUseCase;
import com.financial.challenge.app.usecase.client.ClientDeleterUseCase;
import com.financial.challenge.app.usecase.client.ClientUpdaterUseCase;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@Valid
public class ClientController {

  private final ClientCreatorUseCase clientCreatorUseCase;
  private final ClientUpdaterUseCase clientUpdaterUseCase;
  private final ClientDeleterUseCase clientDeleterUseCase;

  @PostMapping(
      path = "",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClientResponse> createClient(@RequestBody CreateClientRequest request)
      throws Exception {
    ClientResponse clientResponse = clientCreatorUseCase.createClient(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
  }

  @PutMapping(path = "/{documentNumber}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateClient(
      @PathVariable("documentNumber") String documentNumber,
      @RequestBody UpdateClientRequest request)
      throws Exception {
    clientUpdaterUseCase.updateClient(request);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping(path = "/{documentNumber}")
  public ResponseEntity<Void> deleteClient(@PathVariable("documentNumber") String documentNumber) {
    clientDeleterUseCase.deleteClient(documentNumber);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
