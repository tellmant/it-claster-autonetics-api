package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory create(Inventory inventory);
    Inventory readById(long id);
    Inventory update(Inventory inventory);
    void delete(long id);
    List<Inventory> getAll();
    List<Inventory> findAllGoodsByShopID_Id(Long shopId);
    List<Inventory> findAllShopsByGoodsID_Id(Integer goodsId);
    Inventory findByGoodsIdAndShopId (Integer goodsId, Long shopId);
    List<Inventory> findAllByGoodsInShopByBarcode(String barcode, Long shopId);
    List<Inventory> findAllByGoodsInShopByNameContains(String name, Long shopId);
}
