package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Settlement;
import com.autonetics.autonetics.api.model.request.NewSettlementRequest;
import com.autonetics.autonetics.api.model.response.SettlementDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {DistrictMapper.class})
public interface SettlementMapper {
    Settlement toEntity(SettlementDto settlementDto);

    SettlementDto toDto(Settlement settlement);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Settlement partialUpdate(SettlementDto settlementDto, @MappingTarget Settlement settlement);

    @Mapping(source = "districtId", target = "district.id")
    @Mapping(source = "settlementTypeId", target = "settlementType.id")
    Settlement toEntity(NewSettlementRequest newSettlementRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Settlement partialUpdate(@MappingTarget Settlement settlement, NewSettlementRequest newSettlementRequest);
}