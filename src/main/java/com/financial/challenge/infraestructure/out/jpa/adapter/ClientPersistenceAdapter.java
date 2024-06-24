package com.financial.challenge.infraestructure.out.jpa.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.spi.ClientPersistencePort;
import com.financial.challenge.infraestructure.out.jpa.entity.ClientEntity;
import com.financial.challenge.infraestructure.out.jpa.mapper.ClientBuilder;
import com.financial.challenge.infraestructure.out.jpa.mapper.ClientMapper;
import com.financial.challenge.infraestructure.out.jpa.mapper.impl.ClientBuilderImpl;
import com.financial.challenge.infraestructure.out.jpa.repository.ClientRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ClientPersistenceAdapter implements ClientPersistencePort {

  private final ClientRepository clientRepository;
  private final ClientMapper clientMapper;

  @Override
  public Client save(Client client) {
    ClientEntity clientEntity = clientMapper.clientToClientEntity(client);
    ClientEntity clientBd = clientRepository.save(clientEntity);
    return ClientBuilderImpl.toClient(clientBd);
  }

  @Override
  public Optional<Client> getClientByDocumentNumber(String documentNumber) {
    Optional<ClientEntity> clientEntityOptional =
        clientRepository.findByDocumentNumber(documentNumber);
    return clientEntityOptional.map(ClientBuilderImpl::toClient);
  }

  @Override
  public void deleteClient(Long clientId) {
    clientRepository.deleteById(clientId);
  }

  @Override
  public List<Client> findAll() {
    return ClientBuilderImpl.getClients(clientRepository.findAll());
  }

  @Override
  public Optional<Client> getClientById(Long clientId) {
    return clientRepository.findById(clientId).map(ClientBuilderImpl::toClient);
  }
}
