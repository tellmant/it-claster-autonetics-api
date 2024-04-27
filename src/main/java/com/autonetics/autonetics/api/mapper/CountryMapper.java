package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Country;
import com.autonetics.autonetics.api.model.request.NewCountryRequest;
import com.autonetics.autonetics.api.model.response.CountryDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountryMapper {
    Country toEntity(CountryDto countryDto);

    CountryDto toDto(Country country);

    Country toEntity(NewCountryRequest newCountry);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Country partialUpdate(NewCountryRequest newCountryRequest, @MappingTarget Country country);
}