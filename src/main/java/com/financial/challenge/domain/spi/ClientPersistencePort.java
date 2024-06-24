package com.financial.challenge.domain.spi;

import java.util.List;
import java.util.Optional;

import com.financial.challenge.domain.model.Client;

public interface ClientPersistencePort {

  Client save(Client client);

  Optional<Client> getClientByDocumentNumber(String documentNumber);

  void  deleteClient(Long clientId);

  List<Client> findAll();

  Optional<Client> getClientById(Long clientId);
}
