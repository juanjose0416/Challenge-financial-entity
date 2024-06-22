package com.financial.challenge.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.financial.challenge.app.dto.client.response.ClientResponse;
import com.financial.challenge.domain.model.Client;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
@Component
public interface ClientResponseMapper {

    @Mappings({
            @Mapping(source = "documentType", target = "documentType"),
            @Mapping(source = "documentNumber", target = "documentNumber"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "birthDate", target = "birthDate"),
            @Mapping(source = "createdAt", target = "createdAt")
    })
    ClientResponse toClientResponse(Client client);
}
