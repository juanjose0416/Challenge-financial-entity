package com.financial.challenge.app.usecase.impl;

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
    public ClientResponse createClient(CreateClientRequest request) {
        Client client = createClientRequestMapper.toClient(request);
        Client response = clientService.createClient(client);
        return clientResponseMapper.toClientResponse(response);
    }

}
