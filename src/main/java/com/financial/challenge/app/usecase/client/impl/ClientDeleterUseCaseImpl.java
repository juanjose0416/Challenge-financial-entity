package com.financial.challenge.app.usecase.client.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.financial.challenge.app.usecase.client.ClientDeleterUseCase;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.service.ClientService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientDeleterUseCaseImpl implements ClientDeleterUseCase {

  private final ClientService clientService;

  @Override
  public void deleteClient(String documentNumber) throws Exception {
    Optional<Client> client = clientService.getClient(documentNumber);
    if (client.isPresent()) {
      deleteClientIfIsValid(client.get());
    } else {
      throw new Exception(String.format("Client with document %s doesn't exist", documentNumber));
    }
  }

  private void deleteClientIfIsValid(Client client) throws Exception {
    if (client.getAccounts().isEmpty()) {
      clientService.deleteClient(client.getDocumentNumber());
    } else {
      throw new Exception(
          String.format(
              "Client with document %s has accounts associated", client.getDocumentNumber()));
    }
  }
}
