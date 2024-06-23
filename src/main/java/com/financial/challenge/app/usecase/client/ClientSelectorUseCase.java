package com.financial.challenge.app.usecase.client;

import java.util.List;

import com.financial.challenge.app.dto.client.response.ClientResponse;

public interface ClientSelectorUseCase {

    ClientResponse getClient(String documentNumber) throws Exception;

    List<ClientResponse> getAll();
}
