package com.financial.challenge.app.usecase;

import com.financial.challenge.app.dto.client.request.CreateClientRequest;
import com.financial.challenge.app.dto.client.response.ClientResponse;

public interface ClientCreatorUseCase {

    ClientResponse createClient(CreateClientRequest request) throws Exception;
}
