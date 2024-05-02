package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Address;
import com.autonetics.autonetics.api.model.request.NewAddressRequest;
import com.autonetics.autonetics.api.model.response.AddressDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {StreetMapper.class})
public interface AddressMapper {
    Address toEntity(AddressDto addressDto);

    AddressDto toDto(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(AddressDto addressDto, @MappingTarget Address address);

    Address toEntity(NewAddressRequest newAddressRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(NewAddressRequest newAddressRequest, @MappingTarget Address address);
}