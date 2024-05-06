package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Customer;
import com.autonetics.autonetics.api.model.request.NewCustomerRequest;
import com.autonetics.autonetics.api.model.response.CustomerDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

    @Mapping(source = "addressId", target = "address.id")
    Customer toEntity(CustomerDto customerDto);

    @Mapping(source = "address.id", target = "addressId")
    CustomerDto toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "addressId", target = "address.id")
    Customer partialUpdate(CustomerDto customerDto, @MappingTarget Customer customer);


    @Mapping(source = "addressId", target = "address.id")
    Customer toEntity(NewCustomerRequest newCustomerRequest);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "addressId", target = "address.id")
    Customer partialUpdate(NewCustomerRequest newCustomerRequest, @MappingTarget Customer customer);
}