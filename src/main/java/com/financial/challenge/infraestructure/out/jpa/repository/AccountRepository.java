package com.financial.challenge.infraestructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financial.challenge.infraestructure.out.jpa.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
