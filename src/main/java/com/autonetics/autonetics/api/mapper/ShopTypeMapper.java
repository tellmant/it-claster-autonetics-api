package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.ShopType;
import com.autonetics.autonetics.api.model.request.NewShopTypeRequest;
import com.autonetics.autonetics.api.model.response.ShopTypeDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShopTypeMapper {
    ShopType toEntity(ShopTypeDto shopTypeDto);

    ShopTypeDto toDto(ShopType shopType);

    ShopType toEntity(NewShopTypeRequest newShopTypeRequest);

}