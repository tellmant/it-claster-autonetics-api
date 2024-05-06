package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Goods;
import com.autonetics.autonetics.api.model.entity.Supplier;
import com.autonetics.autonetics.api.model.request.NewGoods;
import com.autonetics.autonetics.api.model.request.PatchGoods;
import com.autonetics.autonetics.api.model.response.GoodsDto;
import com.autonetics.autonetics.api.service.SupplierService;
import jdk.jfr.Name;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CountryMapper.class, ClassMapper.class, SupplierMapper.class, GoodsTypeMapper.class})
public interface GoodsMapper {
    Goods toEntity(GoodsDto goodsDto);

    GoodsDto toDto(Goods goods);

    @Mapping(target = "supplierID", source = "supplierID", ignore = true)
    @Mapping(target = "goodsTypeId", source = "goodsTypeId", ignore = true)
    @Mapping(target = "countryID", source = "countryID", ignore = true)
    @Mapping(target = "classID", source = "classID", ignore = true)
    Goods toEntity(NewGoods newGoods);


    @Mapping(target = "supplierID", source = "supplierID", ignore = true)
    @Mapping(target = "goodsTypeId", source = "goodsTypeId", ignore = true)
    @Mapping(target = "countryID", source = "countryID", ignore = true)
    @Mapping(target = "classID", source = "classID", ignore = true)
    Goods toEntity(PatchGoods patchGoods);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Goods partialUpdate(GoodsDto goodsDto, @MappingTarget Goods goods);
}

