package com.financial.challenge.app.usecase.client.impl;

import org.springframework.stereotype.Service;

import com.financial.challenge.app.dto.client.request.CreateClientRequest;
import com.financial.challenge.app.dto.client.response.ClientResponse;
import com.financial.challenge.app.mapper.ClientRequestMapper;
import com.financial.challenge.app.mapper.ClientResponseMapper;
import com.financial.challenge.app.usecase.client.ClientCreatorUseCase;
import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.service.ClientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClientCreatorUseCaseImpl implements ClientCreatorUseCase {

  private final ClientService clientService;
  private final ClientRequestMapper clientRequestMapper;
  private final ClientResponseMapper clientResponseMapper;

  @Override
  public ClientResponse createClient(CreateClientRequest request) throws Exception {
    try {
      validateIfClientExist(request.getDocumentNumber());
    } catch (Exception e) {
      Client clientMapped = clientRequestMapper.toClient(request);
      Client response = clientService.createClient(clientMapped);
      return clientResponseMapper.toClientResponse(response);
    }
    throw new Exception(
        String.format("Client with document %s already exist", request.getDocumentNumber()));
  }

  private void validateIfClientExist(String documentNumber) throws Exception {
    clientService.getClient(documentNumber);
  }
}
