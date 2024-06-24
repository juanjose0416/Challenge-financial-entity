package com.financial.challenge.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.financial.challenge.app.dto.client.response.ClientResponse;
import com.financial.challenge.domain.model.Client;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ClientResponseMapper {

  ClientResponse toClientResponse(Client client);

  List<ClientResponse> clientsToClientResponses(List<Client> clients);
}
