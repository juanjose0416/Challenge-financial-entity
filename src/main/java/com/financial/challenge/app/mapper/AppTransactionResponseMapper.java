package com.financial.challenge.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.financial.challenge.app.dto.transaction.response.TransactionResponse;
import com.financial.challenge.domain.model.Transaction;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AppTransactionResponseMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "transactionType", target = "transactionType")
    @Mapping(source = "originAccount.accountNumber", target = "sourceAccountId")
    @Mapping(source = "createdAt", target = "transactionDate")
    @Mapping(source = "status", target = "transactionStatus")
    TransactionResponse toTransactionResponse(Transaction transaction);


}
