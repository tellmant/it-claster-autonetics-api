package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.DeliveryGoods;
import com.autonetics.autonetics.api.model.request.NewDeliveryGoods;
import com.autonetics.autonetics.api.model.response.DeliveryGoodsDetailDto;
import com.autonetics.autonetics.api.model.response.DeliveryGoodsDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DeliveryGoodsMapper {
    DeliveryGoods toEntity(DeliveryGoodsDto deliveryGoodsDto);

    DeliveryGoodsDto toDto(DeliveryGoods deliveryGoods);

    DeliveryGoodsDetailDto toDtoWithDetails(DeliveryGoods deliveryGoods);

    @Mapping(target = "goodsID", ignore = true)
    @Mapping(target = "supplierID", ignore = true)
    DeliveryGoods toEntity(NewDeliveryGoods newDeliveryGoods);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DeliveryGoods partialUpdate(DeliveryGoodsDto deliveryGoodsDto, @MappingTarget DeliveryGoods deliveryGoods);
}