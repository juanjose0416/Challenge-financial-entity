package com.financial.challenge.infraestructure.out.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.challenge.infraestructure.out.jpa.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

  Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
