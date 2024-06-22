package com.financial.challenge.domain.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.service.ClientService;
import com.financial.challenge.domain.spi.ClientPersistencePort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientPersistencePort clientPersistencePort;

    @Override
    public Client createClient(Client client) {
        client.setCreatedAt(LocalDateTime.now());
        client.setUpdatedAt(LocalDateTime.now());
        return clientPersistencePort.save(client);
    }

}
