package com.financial.challenge.app.usecase.client.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.financial.challenge.app.dto.client.request.UpdateClientRequest;
import com.financial.challenge.app.mapper.ClientRequestMapper;
import com.financial.challenge.app.usecase.client.ClientUpdaterUseCase;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.service.ClientService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientUpdaterUseCaseImpl implements ClientUpdaterUseCase {

  private final ClientService clientService;
  private final ClientRequestMapper clientRequestMapper;

  @Override
  public void updateClient(UpdateClientRequest request, Long clientId) {
    Client existingClient = clientService.getClientById(clientId);
    Client mappedClient = clientRequestMapper.toClient(request);
    update(existingClient, mappedClient);
    clientService.updateClient(existingClient);
  }

  private void update(Client existingClient, Client mappedClient) {
    existingClient.setName(
        Optional.ofNullable(mappedClient.getName()).orElse(existingClient.getName()));
    existingClient.setLastName(
        Optional.ofNullable(mappedClient.getLastName()).orElse(existingClient.getLastName()));
    existingClient.setEmail(
        Optional.ofNullable(mappedClient.getEmail()).orElse(existingClient.getEmail()));
    existingClient.setBirthDate(
        Optional.ofNullable(mappedClient.getBirthDate()).orElse(existingClient.getBirthDate()));
  }
}
