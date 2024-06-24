package com.financial.challenge.infraestructure.out.jpa.mapper.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.financial.challenge.domain.model.Client;
import com.financial.challenge.infraestructure.out.jpa.entity.ClientEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClientBuilderImpl {

  public static Client toClient(ClientEntity clientEntity) {
    return Client.builder()
        .id(clientEntity.getId())
        .documentType(clientEntity.getDocumentType())
        .name(clientEntity.getName())
        .lastName(clientEntity.getLastName())
        .email(clientEntity.getEmail())
        .birthDate(clientEntity.getBirthDate())
        .documentNumber(clientEntity.getDocumentNumber())
        .createdAt(clientEntity.getCreatedAt())
        .updatedAt(clientEntity.getUpdatedAt())
        .accounts(AccountFactoryImpl.getAccountsWithoutClients(clientEntity.getAccounts()))
        .build();
  }

  public static Client getClientWithoutAccount(ClientEntity clientEntity) {
    return Client.builder()
        .id(clientEntity.getId())
        .documentType(clientEntity.getDocumentType())
        .name(clientEntity.getName())
        .lastName(clientEntity.getLastName())
        .email(clientEntity.getEmail())
        .birthDate(clientEntity.getBirthDate())
        .documentNumber(clientEntity.getDocumentNumber())
        .createdAt(clientEntity.getCreatedAt())
        .updatedAt(clientEntity.getUpdatedAt())
        .build();
  }

  public static List<Client> getClients(List<ClientEntity> clientEntityList) {
    if (Objects.isNull(clientEntityList)) {
      return Collections.emptyList();
    }
    return clientEntityList.stream().map(ClientBuilderImpl::toClient).collect(Collectors.toList());
  }
}
