package com.autonetics.autonetics.api.mapper;

import com.autonetics.autonetics.api.model.entity.Goods;
import com.autonetics.autonetics.api.model.request.NewGoods;
import com.autonetics.autonetics.api.model.request.PatchGoods;
import com.autonetics.autonetics.api.model.response.GoodsDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GoodsMapper {
    Goods toEntity(GoodsDto goodsDto);

    GoodsDto toDto(Goods goods);

    NewGoods toNewGoods(GoodsDto goodsDto);

    PatchGoods toPatchGoods(GoodsDto goodsDto);

    Goods toEntity(NewGoods newGoods);

    Goods toEntity(PatchGoods patchGoods);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Goods partialUpdate(GoodsDto goodsDto, @MappingTarget Goods goods);
}