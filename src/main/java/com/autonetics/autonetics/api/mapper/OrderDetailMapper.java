package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.OrderDetail;
import com.autonetics.autonetics.api.model.request.NewOrderDetail;
import com.autonetics.autonetics.api.model.response.OrderDetailDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderDetailMapper {
    OrderDetail toEntity(OrderDetailDto orderDetailDto);

    OrderDetailDto toDto(OrderDetail orderDetail);

    @Mapping(target = "orderID", ignore = true)
    @Mapping(target = "goodsID", ignore = true)
    OrderDetail toEntity(NewOrderDetail newOrderDetail);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderDetail partialUpdate(OrderDetailDto orderDetailDto, @MappingTarget OrderDetail orderDetail);
}