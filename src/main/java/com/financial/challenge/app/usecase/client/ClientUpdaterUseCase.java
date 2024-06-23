package com.financial.challenge.app.usecase.client;

import com.financial.challenge.app.dto.client.request.UpdateClientRequest;

public interface ClientUpdaterUseCase {

    void updateClient(UpdateClientRequest request, Long clientId);
}
