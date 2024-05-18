package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Inventory;
import com.autonetics.autonetics.api.model.request.NewInventoryRequest;
import com.autonetics.autonetics.api.model.response.InventoryDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {GoodsMapper.class, ShopMapper.class})
public interface InventoryMapper {
    Inventory toEntity(InventoryDto inventoryDto);

    InventoryDto toDto(Inventory inventory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Inventory partialUpdate(InventoryDto inventoryDto, @MappingTarget Inventory inventory);

    @Mapping(source = "shopId", target = "shopID.id")
    @Mapping(source = "goodsId", target = "goodsID.id")
    Inventory toEntity(NewInventoryRequest newInventoryRequest);


    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Inventory partialUpdate(NewInventoryRequest newInventoryRequest, @MappingTarget Inventory inventory);
}