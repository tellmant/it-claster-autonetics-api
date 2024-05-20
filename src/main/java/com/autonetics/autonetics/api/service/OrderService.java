package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order);

    Order readById(long id);

    Order update(Order order);

    void delete(long id);

    List<Order> getAll();

    Order readByBarcode(String barcode);

    List<Order> readByClientID_Email(String email);

    List<Order> readByNameContains(String name);
}
