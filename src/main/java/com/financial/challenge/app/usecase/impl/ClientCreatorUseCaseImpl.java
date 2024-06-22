package com.financial.challenge.app.usecase.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.financial.challenge.app.dto.client.request.CreateClientRequest;
import com.financial.challenge.app.dto.client.response.ClientResponse;
import com.financial.challenge.app.mapper.ClientResponseMapper;
import com.financial.challenge.app.mapper.CreateClientRequestMapper;
import com.financial.challenge.app.usecase.ClientCreatorUseCase;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.service.ClientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClientCreatorUseCaseImpl implements ClientCreatorUseCase {

  private final ClientService clientService;
  private final CreateClientRequestMapper createClientRequestMapper;
  private final ClientResponseMapper clientResponseMapper;

  @Override
  public ClientResponse createClient(CreateClientRequest request) throws Exception {
    Optional<Client> client = clientService.getClient(request.getDocumentNumber());
    if (client.isEmpty()) {
      Client clientMapped = createClientRequestMapper.toClient(request);
      Client response = clientService.createClient(clientMapped);
      return clientResponseMapper.toClientResponse(response);
    }
    throw new Exception(
        String.format("Client with document %s already exist", request.getDocumentNumber()));
  }
}
