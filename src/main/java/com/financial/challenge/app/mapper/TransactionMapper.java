package com.financial.challenge.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.financial.challenge.app.dto.transaction.response.TransactionResponse;
import com.financial.challenge.domain.model.Transaction;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
@Component
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "transactionType", target = "transactionType", qualifiedByName = "mapTransactionType")
    @Mapping(source = "originAccount.id", target = "sourceAccountId")
    @Mapping(source = "createdAt", target = "transactionDate")
    TransactionResponse toTransactionResponse(Transaction transaction);


}
