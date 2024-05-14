package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Shop;

import java.math.BigDecimal;
import java.util.List;

public interface ShopService {
    Shop create(Shop shop);

    Shop readById(long id);

    Shop update(Shop shop);

    void delete(long id);

    List<Shop> getAll();
    List<Shop> getAllByAddressId(Long addressId);
    List<Shop> getAllByCustomerId(Long customerId);
    List<Shop> getAllByShopTypeId(Long shopTypeId);
    List<Shop> getAllByNameContaining(String name);
    List<Shop> getAllIn500Meters(BigDecimal latitude, BigDecimal longitude);
}
