package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.DeliveryHistory;
import com.autonetics.autonetics.api.model.response.DeliveryHistoryDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DeliveryHistoryMapper {
    DeliveryHistory toEntity(DeliveryHistoryDto deliveryHistoryDto);

//    @Mapping(target = "")
    DeliveryHistoryDto toDto(DeliveryHistory deliveryHistory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DeliveryHistory partialUpdate(DeliveryHistoryDto deliveryHistoryDto, @MappingTarget DeliveryHistory deliveryHistory);
}