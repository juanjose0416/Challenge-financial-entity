package com.financial.challenge.infraestructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.financial.challenge.domain.model.Client;
import com.financial.challenge.infraestructure.out.jpa.entity.ClientEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
@Component
public interface ClientEntityMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "documentType", target = "documentType"),
            @Mapping(source = "documentNumber", target = "documentNumber"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "birthDate", target = "birthDate"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt")
    })
    Client clientEntityToClient(ClientEntity clientEntity);
}
