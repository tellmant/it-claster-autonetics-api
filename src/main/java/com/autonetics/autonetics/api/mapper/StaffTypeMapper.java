package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.StaffType;
import com.autonetics.autonetics.api.model.request.NewStaffTypeRequest;
import com.autonetics.autonetics.api.model.response.StaffTypeDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StaffTypeMapper {
    StaffType toEntity(StaffTypeDto staffTypeDto);

    StaffTypeDto toDto(StaffType staffType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StaffType partialUpdate(StaffTypeDto staffTypeDto, @MappingTarget StaffType staffType);

    StaffType toEntity(NewStaffTypeRequest newStaffTypeRequest);

    NewStaffTypeRequest toDto1(StaffType staffType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StaffType partialUpdate(NewStaffTypeRequest newStaffTypeRequest, @MappingTarget StaffType staffType);
}