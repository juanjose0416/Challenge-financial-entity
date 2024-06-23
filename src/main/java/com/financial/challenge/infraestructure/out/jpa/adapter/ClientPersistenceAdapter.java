package com.financial.challenge.infraestructure.out.jpa.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.financial.challenge.domain.model.Client;
import com.financial.challenge.domain.spi.ClientPersistencePort;
import com.financial.challenge.infraestructure.out.jpa.entity.ClientEntity;
import com.financial.challenge.infraestructure.out.jpa.mapper.ClientEntityMapper;
import com.financial.challenge.infraestructure.out.jpa.mapper.ClientMapper;
import com.financial.challenge.infraestructure.out.jpa.repository.ClientRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ClientPersistenceAdapter implements ClientPersistencePort {

  private final ClientRepository clientRepository;
  private final ClientMapper clientMapper;
  private final ClientEntityMapper clientEntityMapper;

  @Override
  public Client save(Client client) {
    ClientEntity clientEntity = clientMapper.clientToClientEntity(client);
    ClientEntity clientBd = clientRepository.save(clientEntity);
    return clientEntityMapper.clientEntityToClient(clientBd);
  }

  @Override
  public Optional<Client> getClientByDocumentNumber(String documentNumber) {
    Optional<ClientEntity> clientEntityOptional =
        clientRepository.findByDocumentNumber(documentNumber);
    return clientEntityOptional.map(clientEntityMapper::clientEntityToClient);
  }

  @Override
  public void deleteClient(String documentNumber) {
    clientRepository.deleteByDocumentNumber(documentNumber);
  }

  @Override
  public List<Client> findAll() {
    return clientEntityMapper.clientEntitiesToClients(clientRepository.findAll());
  }
}
