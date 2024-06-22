package com.financial.challenge.domain.spi;

import com.financial.challenge.domain.model.Client;

public interface ClientPersistencePort {

    Client save(Client client);
}
