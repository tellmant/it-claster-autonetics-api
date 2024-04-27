package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Goods;

import java.util.List;

public interface GoodsService {

    Goods create(Goods goods);

    Goods readById(long id);

    Goods update(Goods goods);

    void delete(long id);

    List<Goods> getAll();
}
