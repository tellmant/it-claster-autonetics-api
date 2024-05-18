package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Shop;
import com.autonetics.autonetics.api.model.request.NewShopRequest;
import com.autonetics.autonetics.api.model.response.LocationAndDistanceDto;
import com.autonetics.autonetics.api.model.response.ShopDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClientMapper.class, AddressMapper.class, CustomerMapper.class, ShopTypeMapper.class})
public interface ShopMapper {
    Shop toEntity(ShopDto shopDto);

    ShopDto toDto(Shop shop);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Shop partialUpdate(ShopDto shopDto, @MappingTarget Shop shop);

    @Mapping(source = "shopTypeId", target = "shopType.id")
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "addressId", target = "address.id")
    @Mapping(source = "clientId", target = "clientId.id")
    Shop toEntity(NewShopRequest newShopRequest);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Shop partialUpdate(NewShopRequest newShopRequest, @MappingTarget Shop shop);

    Shop toEntity(LocationAndDistanceDto locationAndDistanceDto);

    LocationAndDistanceDto toLocationAndDistance(Shop shop);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Shop partialUpdate(LocationAndDistanceDto locationAndDistanceDto, @MappingTarget Shop shop);
}