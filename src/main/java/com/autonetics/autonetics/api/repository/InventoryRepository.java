package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findAllByShopID_Id(Long shopId);
    List<Inventory> findAllByGoodsID_Id(Integer goodsId);
    Inventory findByGoodsID_IdAndShopID_Id(Integer goodsId, Long shopId);
    @Query("SELECT i FROM Inventory i WHERE i.shopID.id = :shopId AND i.goodsID.barcode = :barcode")
    List<Inventory> findAllByGoodsID_BarcodeAndShopID_Id(@Param("barcode") String barcode, @Param("shopId") Long shopId);

    @Query("SELECT i FROM Inventory i WHERE i.shopID.id = :shopId AND i.goodsID.name LIKE %:name%")
    List<Inventory> findAllByGoodsID_NameContainsAndShopID_id(@Param("name") String name, @Param("shopId") Long shopId);
}