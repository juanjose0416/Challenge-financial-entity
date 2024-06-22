package com.financial.challenge.infraestructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.financial.challenge.domain.model.Client;
import com.financial.challenge.infraestructure.out.jpa.entity.ClientEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
@Component
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "documentType", target = "documentType"),
            @Mapping(source = "documentNumber", target = "documentNumber"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "birthDate", target = "birthDate"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt", target = "updatedAt")
    })
    ClientEntity clientToClientEntity(Client client);
}
