package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Supplier;
import com.autonetics.autonetics.api.model.request.NewSupplierRequest;
import com.autonetics.autonetics.api.model.response.SupplierDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupplierMapper {
    Supplier toEntity(SupplierDto supplierDto);

    SupplierDto toDto(Supplier supplier);

    Supplier toEntity(NewSupplierRequest newSupplierRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Supplier partialUpdate(SupplierDto supplierDto, @MappingTarget Supplier supplier);
}