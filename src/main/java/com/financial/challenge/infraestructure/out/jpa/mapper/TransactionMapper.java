package com.financial.challenge.infraestructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.financial.challenge.domain.model.Transaction;
import com.financial.challenge.infraestructure.out.jpa.entity.TransactionEntity;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
@Component
public interface TransactionMapper {

  @Mapping(source = "originAccount", target = "originAccount")
  @Mapping(source = "destinationAccount", target = "destinationAccount")
  TransactionEntity toTransactionEntity(Transaction transaction);
}
