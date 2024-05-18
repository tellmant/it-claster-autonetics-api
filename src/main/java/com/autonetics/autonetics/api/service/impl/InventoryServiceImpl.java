package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.Inventory;
import com.autonetics.autonetics.api.repository.InventoryRepository;
import com.autonetics.autonetics.api.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory create(Inventory inventory) {
        if (inventory != null) {
            return inventoryRepository.save(inventory);
        }
        throw new NullEntityReferenceException("Inventory cannot be 'null'");
    }

    @Override
    public Inventory readById(long id) {
        return inventoryRepository.findById(id).orElseThrow(
                () -> new NullEntityReferenceException("Inventory with id " + id + " not found"));
    }

    @Override
    public Inventory update(Inventory inventory) {
        if (inventory != null) {
            return inventoryRepository.save(inventory);
        }
        throw new NullEntityReferenceException("Inventory cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        Inventory inventory = readById(id);
        inventoryRepository.delete(inventory);
    }

    @Override
    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> findAllGoodsByShopID_Id(Long shopId) {
        return inventoryRepository.findAllByShopID_Id(shopId);
    }

    @Override
    public List<Inventory> findAllShopsByGoodsID_Id(Integer goodsId) {
        return inventoryRepository.findAllByGoodsID_Id(goodsId);
    }
}
