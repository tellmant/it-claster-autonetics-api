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

    @Mapping(source = "regionId", target = "region.id")
    District toEntity(NewDistrictRequest newDistrictRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "regionId", target = "region.id")
    District partialUpdate(NewDistrictRequest newDistrictRequest, @MappingTarget District district);
}