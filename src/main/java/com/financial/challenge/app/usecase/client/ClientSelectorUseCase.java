package com.financial.challenge.app.usecase.client;

import java.util.List;

import com.financial.challenge.app.dto.client.response.ClientResponse;

public interface ClientSelectorUseCase {

    ClientResponse getClient(Long clientId);

    List<ClientResponse> getAll();
}
