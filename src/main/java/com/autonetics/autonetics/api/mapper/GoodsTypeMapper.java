package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.GoodsType;
import com.autonetics.autonetics.api.model.response.GoodsTypeDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GoodsTypeMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "updatedBy", target = "updatedBy")
    @Mapping(source = "updatedOn", target = "updatedOn")
    GoodsType toEntity(GoodsTypeDto goodsTypeDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "updatedBy", target = "updatedBy")
    @Mapping(source = "updatedOn", target = "updatedOn")
    GoodsTypeDto toDto(GoodsType goodsType);
}