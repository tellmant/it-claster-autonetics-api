package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findAllByShopID_Id(Long shopId);
    List<Inventory> findAllByGoodsID_Id(Integer goodsId);
}