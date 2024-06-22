package com.financial.challenge.domain.service;

import java.util.Optional;

import com.financial.challenge.domain.model.Client;

public interface ClientService {
  Client createClient(Client client);

  Optional<Client> getClient(String documentNumber);
}
