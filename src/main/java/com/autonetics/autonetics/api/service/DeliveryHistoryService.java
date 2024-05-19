package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.DeliveryHistory;

import java.util.List;

public interface DeliveryHistoryService {

    DeliveryHistory create(DeliveryHistory deliveryHistory);

    DeliveryHistory readById(long id);

    DeliveryHistory update(DeliveryHistory deliveryHistory);

    void delete(long id);

    List<DeliveryHistory> getAll();

    DeliveryHistory readByGoodsID_Barcode(String barcode);

    DeliveryHistory readByDeliveryGoodsid_Barcode(String barcode);
}
