package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {

    OrderDetail create(OrderDetail orderDetail);

    OrderDetail readById(long id);

    OrderDetail update(OrderDetail orderDetail);

    void delete(long id);

    List<OrderDetail> getAll();

    OrderDetail findByClientBarcode(String barcode);
    OrderDetail findByGoodsBarcode(String barcode);
}
