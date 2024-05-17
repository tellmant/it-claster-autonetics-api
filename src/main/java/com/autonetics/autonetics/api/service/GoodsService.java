package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Goods;

import java.util.List;

public interface GoodsService {

    Goods create(Goods goods);

    Goods readById(long id);

    Goods update(Goods goods);

    void delete(long id);

    List<Goods> getAll();

    Goods readByBarcode(String barcode);

    List<Goods> readByGoodsTypeId_Name(String name);

    List<Goods> readByClassID_Name(String name);

    List<Goods> readByName(String name);

    List<Goods> readByProducer(String producer);
}
