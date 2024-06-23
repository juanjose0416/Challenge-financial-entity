package com.financial.challenge.domain.service;

import java.util.List;
import java.util.Optional;

import com.financial.challenge.domain.model.Client;

public interface ClientService {
  Client createClient(Client client);

  Client getClient(String documentNumber);

  void updateClient(Client client);

  void deleteClient(String documentNumber);

  List<Client> getAll();

  Client getClientById(Long clientId);

}
