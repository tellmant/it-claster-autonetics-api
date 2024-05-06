package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.ShopType;

import java.util.List;

public interface ShopTypeService {
    ShopType readById(long id);
    ShopType create(ShopType shopType);
    ShopType update(ShopType shopType);
    void delete(long id);
    List<ShopType> getAll();

}
