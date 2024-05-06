package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Staff;
import com.autonetics.autonetics.api.model.request.NewStaffRequest;
import com.autonetics.autonetics.api.model.response.StaffDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StaffMapper {
    @Mapping(source = "shopId", target = "shop.id")
    Staff toEntity(StaffDto staffDto);

    @Mapping(source = "shop.id", target = "shopId")
    StaffDto toDto(Staff staff);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "shopId", target = "shop.id")
    Staff partialUpdate(StaffDto staffDto, @MappingTarget Staff staff);


    @Mapping(source = "shopId", target = "shop.id")
    @Mapping(source = "staffTypeId", target = "staffType.id")
    Staff toEntity(NewStaffRequest newStaffRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Staff partialUpdate(NewStaffRequest newStaffRequest, @MappingTarget Staff staff);
}