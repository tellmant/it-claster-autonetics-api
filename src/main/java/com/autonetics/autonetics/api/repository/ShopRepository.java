package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findAllByAddress_Id(Long addressId);
    List<Shop> findAllByCustomer_Id(Long customerId);
    List<Shop> findAllByShopType_Id(Long shopTypeId);
    List<Shop> findAllByNameContaining(String name);
}