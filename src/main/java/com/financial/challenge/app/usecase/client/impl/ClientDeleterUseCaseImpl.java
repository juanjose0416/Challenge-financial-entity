package com.financial.challenge.app.usecase.client.impl;

import org.springframework.stereotype.Service;

import com.financial.challenge.app.usecase.client.ClientDeleterUseCase;
import com.financial.challenge.domain.exception.ClientException;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.service.ClientService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientDeleterUseCaseImpl implements ClientDeleterUseCase {

  private final ClientService clientService;

  @Override
  public void deleteClient(Long clientId) {
    Client client = clientService.getClientById(clientId);
    deleteClientIfIsValid(client);
  }

  private void deleteClientIfIsValid(Client client) {
    if (client.getAccounts().isEmpty()) {
      clientService.deleteClient(client.getDocumentNumber());
    } else {
      throw new ClientException(
          String.format(
              "Client with document %s has accounts associated", client.getDocumentNumber()));
    }
  }
}
