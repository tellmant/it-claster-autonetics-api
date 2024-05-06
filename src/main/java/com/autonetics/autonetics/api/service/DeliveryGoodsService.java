package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.DeliveryGoods;

import java.util.List;

public interface DeliveryGoodsService {

    DeliveryGoods create(DeliveryGoods deliveryGoods);

    DeliveryGoods readById(long id);

    DeliveryGoods update(DeliveryGoods deliveryGoods);

    void delete(long id);

    List<DeliveryGoods> getAll();

    DeliveryGoods readByBarcode(String barcode);

    DeliveryGoods readByNumber(Integer number);

    List<DeliveryGoods> readBySupplierNameContains(String name);

    List<DeliveryGoods> readByGoodsTypeNameContains(String name);

    DeliveryGoods readByGoodsBarcode(String barcode);

    List<DeliveryGoods> readByGoodsNameContains(String name);

    List<DeliveryGoods> readByGoodsProducerContains(String producer);
}
