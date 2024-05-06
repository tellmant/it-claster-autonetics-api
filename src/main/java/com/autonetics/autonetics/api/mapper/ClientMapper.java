package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Client;
import com.autonetics.autonetics.api.model.request.NewClientRequest;
import com.autonetics.autonetics.api.model.response.ClientDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    Client toEntity(ClientDto clientDto);

    Client toEntity(NewClientRequest newClientRequest);

    ClientDto toDto(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Client partialUpdate(ClientDto clientDto, @MappingTarget Client client);
}