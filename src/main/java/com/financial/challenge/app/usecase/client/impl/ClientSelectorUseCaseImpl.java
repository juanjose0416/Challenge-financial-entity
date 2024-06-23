package com.financial.challenge.app.usecase.client.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.financial.challenge.app.dto.client.response.ClientResponse;
import com.financial.challenge.app.mapper.ClientResponseMapper;
import com.financial.challenge.app.usecase.client.ClientSelectorUseCase;
import com.financial.challenge.domain.service.ClientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClientSelectorUseCaseImpl implements ClientSelectorUseCase {

  private final ClientService clientService;
  private final ClientResponseMapper clientResponseMapper;

  @Override
  public ClientResponse getClient(String documentNumber) throws Exception {
    return clientResponseMapper.toClientResponse(clientService.getClient(documentNumber));
  }

  @Override
  public List<ClientResponse> getAll() {
    return clientResponseMapper.clientsToClientResponses(clientService.getAll());
  }
}
