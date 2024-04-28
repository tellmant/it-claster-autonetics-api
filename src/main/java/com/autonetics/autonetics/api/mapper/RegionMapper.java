package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Region;
import com.autonetics.autonetics.api.model.request.NewRegionRequest;
import com.autonetics.autonetics.api.model.response.RegionDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CountryMapper.class})
public interface RegionMapper {
    Region toEntity(RegionDto regionDto);

    @Mapping(source = "country", target = "country")
    RegionDto toDto(Region region);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Region partialUpdate(RegionDto regionDto, @MappingTarget Region region);

    Region toEntity(NewRegionRequest newRegionRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Region partialUpdate(NewRegionRequest newRegionRequest, @MappingTarget Region region);
}