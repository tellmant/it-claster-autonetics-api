package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Class;
import com.autonetics.autonetics.api.model.request.NewClassRequest;
import com.autonetics.autonetics.api.model.response.ClassDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClassMapper {
    Class toEntity(ClassDto classDto);

    ClassDto toDto(Class classEntity);

    Class toEntity(NewClassRequest newClassRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Class partialUpdate(ClassDto classDto, @MappingTarget Class classEntity);
}