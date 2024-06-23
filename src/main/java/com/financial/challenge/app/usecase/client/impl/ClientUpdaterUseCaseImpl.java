package com.financial.challenge.app.usecase.client.impl;

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
  public void updateClient(UpdateClientRequest request, Long clientId) throws Exception {
    Client client = clientService.getClientById(clientId);
    Client clientMapped = clientRequestMapper.toClient(request);
    clientMapped.setId(client.getId());
    clientService.updateClient(clientMapped);
  }
}
