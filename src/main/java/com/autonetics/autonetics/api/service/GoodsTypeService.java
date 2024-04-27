package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.GoodsType;

import java.util.List;

public interface GoodsTypeService {

    GoodsType create(GoodsType goodsType);

    GoodsType readById(long id);

    GoodsType update(GoodsType goodsType);

    void delete(long id);

    List<GoodsType> getAll();

}
