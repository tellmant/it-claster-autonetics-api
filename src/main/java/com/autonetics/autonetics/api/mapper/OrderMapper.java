package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Order;
import com.autonetics.autonetics.api.model.request.NewOrder;
import com.autonetics.autonetics.api.model.response.OrderDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);

    @Mapping(target = "clientID", ignore = true)
    Order toEntity(NewOrder newOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderDto orderDto, @MappingTarget Order order);
}