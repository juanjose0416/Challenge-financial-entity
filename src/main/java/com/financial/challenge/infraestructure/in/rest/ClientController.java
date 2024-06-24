package com.financial.challenge.infraestructure.in.rest;

import java.util.List;

import javax.validation.Valid;

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

import com.financial.challenge.app.dto.client.request.CreateClientRequest;
import com.financial.challenge.app.dto.client.request.UpdateClientRequest;
import com.financial.challenge.app.dto.client.response.ClientResponse;
import com.financial.challenge.app.usecase.client.ClientCreatorUseCase;
import com.financial.challenge.app.usecase.client.ClientDeleterUseCase;
import com.financial.challenge.app.usecase.client.ClientSelectorUseCase;
import com.financial.challenge.app.usecase.client.ClientUpdaterUseCase;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@Validated
public class ClientController {

  private final ClientCreatorUseCase clientCreatorUseCase;
  private final ClientUpdaterUseCase clientUpdaterUseCase;
  private final ClientDeleterUseCase clientDeleterUseCase;
  private final ClientSelectorUseCase clientSelectorUseCase;

  @PostMapping(
      path = "",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClientResponse> createClient(
      @Valid @RequestBody CreateClientRequest request) {
    ClientResponse clientResponse = clientCreatorUseCase.createClient(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
  }

  @PutMapping(path = "/{clientId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateClient(
      @PathVariable("clientId") Long clientId,
      @Valid @RequestBody UpdateClientRequest request) {
    clientUpdaterUseCase.updateClient(request, clientId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping(path = "/{clientId}")
  public ResponseEntity<Void> deleteClient(@PathVariable("clientId") Long clientId) {
    clientDeleterUseCase.deleteClient(clientId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping(path = "/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClientResponse> getClient(@PathVariable("clientId") Long clientId) {
    ClientResponse clientResponse = clientSelectorUseCase.getClient(clientId);
    return ResponseEntity.status(HttpStatus.OK).body(clientResponse);
  }

  @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ClientResponse>> getAll() {
    List<ClientResponse> clientResponseList = clientSelectorUseCase.getAll();
    return ResponseEntity.status(HttpStatus.OK).body(clientResponseList);
  }
}
