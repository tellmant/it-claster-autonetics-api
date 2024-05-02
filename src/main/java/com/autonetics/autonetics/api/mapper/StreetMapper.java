package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Street;
import com.autonetics.autonetics.api.model.entity.StreetType;
import com.autonetics.autonetics.api.model.request.NewStreetRequest;
import com.autonetics.autonetics.api.model.response.StreetDto;
import com.autonetics.autonetics.api.model.response.StreetTypeDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StreetMapper {
    @Mapping(source = "streetType.name", target = "streetType")
    @Mapping(target = "streetType.id")
    Street toEntity(StreetDto streetDto);

    @Mapping(source = "streetType.name", target = "streetType")
    @Mapping(target = "streetType.id")
    StreetDto toDto(Street street);

    StreetTypeDto toDto(StreetType streetType);

    StreetType toEntity(StreetTypeDto streetTypeDto);

    Street toEntity(NewStreetRequest newStreetRequest);
}