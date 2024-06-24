package com.financial.challenge.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.financial.challenge.app.dto.account.response.AccountResponse;
import com.financial.challenge.domain.model.Account;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AccountResponseMapper {

  @Mapping(target = "accountType", source = "accountType")
  @Mapping(target = "accountNumber", source = "accountNumber")
  @Mapping(target = "status", source = "status")
  @Mapping(target = "balance", source = "balance")
  @Mapping(target = "createdAt", source = "createdAt")
  @Mapping(target = "id", source = "id")
  AccountResponse accountToAccountResponse(Account account);

  List<AccountResponse> accountsToAccountResponses(List<Account> accounts);
}
