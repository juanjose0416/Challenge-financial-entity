package com.financial.challenge.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.financial.challenge.app.dto.account.response.AccountResponse;
import com.financial.challenge.domain.model.Account;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
@Component
public interface AccountResponseMapper {

    @Mapping(target = "accountType", source = "accountType")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "isGMF", source = "isGMF")
    @Mapping(target = "createdAt", source = "createdAt")
    AccountResponse accountToAccountResponse(Account account);
}
