package com.financial.challenge.infraestructure.out.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.challenge.infraestructure.out.jpa.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

  Optional<ClientEntity> findByDocumentNumber(String documentNumber);

  void deleteByDocumentNumber(String documentNumber);
}
