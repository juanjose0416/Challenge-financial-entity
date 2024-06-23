package com.financial.challenge.domain.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.financial.challenge.domain.exception.ClientException;
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

  @Override
  public Client getClient(String documentNumber) {
    Optional<Client> client = clientPersistencePort.getClientByDocumentNumber(documentNumber);
    if(client.isPresent()){
      return client.get();
    }
    throw new ClientException(String.format("Client with document %s doesn't exist", documentNumber));
  }

  @Override
  public void updateClient(Client client) {
    client.setUpdatedAt(LocalDateTime.now());
    clientPersistencePort.save(client);
  }

  @Override
  public void deleteClient(String documentNumber) {
    clientPersistencePort.deleteClient(documentNumber);
  }

  @Override
  public List<Client> getAll() {
    return clientPersistencePort.findAll();
  }

  @Override
  public Client getClientById(Long clientId) {
    Optional<Client> client = clientPersistencePort.getClientById(clientId);
    if(client.isPresent()){
      return client.get();
    }
    throw new ClientException(String.format("Client with id %s doesn't exist", clientId));
  }

}
