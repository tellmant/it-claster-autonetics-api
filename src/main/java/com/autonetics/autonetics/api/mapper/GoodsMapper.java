package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Goods;
import com.autonetics.autonetics.api.model.request.NewGoods;
import com.autonetics.autonetics.api.model.request.PatchGoods;
import com.autonetics.autonetics.api.model.request.RequestGoodsAi;
import com.autonetics.autonetics.api.model.response.GoodsDto;
import org.mapstruct.*;


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

    Goods toEntity(RequestGoodsAi requestGoodsAi);

    RequestGoodsAi toAiDto(Goods goods);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Goods partialUpdate(RequestGoodsAi requestGoodsAi, @MappingTarget Goods goods);
}

