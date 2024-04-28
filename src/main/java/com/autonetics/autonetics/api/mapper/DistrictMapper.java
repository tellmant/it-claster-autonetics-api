package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.District;
import com.autonetics.autonetics.api.model.request.NewDistrictRequest;
import com.autonetics.autonetics.api.model.response.DistrictDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {RegionMapper.class})
public interface DistrictMapper {
    District toEntity(DistrictDto districtDto);

    DistrictDto toDto(District district);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    District partialUpdate(DistrictDto districtDto, @MappingTarget District district);

    District toEntity(NewDistrictRequest newDistrictRequest);

    NewDistrictRequest toDto1(District district);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    District partialUpdate(NewDistrictRequest newDistrictRequest, @MappingTarget District district);
}