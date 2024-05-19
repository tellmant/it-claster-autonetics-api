package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.InventoryMapper;
import com.autonetics.autonetics.api.model.entity.Goods;
import com.autonetics.autonetics.api.model.entity.Inventory;
import com.autonetics.autonetics.api.model.entity.Shop;
import com.autonetics.autonetics.api.model.request.NewInventoryRequest;
import com.autonetics.autonetics.api.model.response.InventoryDto;
import com.autonetics.autonetics.api.service.GoodsService;
import com.autonetics.autonetics.api.service.InventoryService;
import com.autonetics.autonetics.api.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventories")
public class InventoriesController {
    private final InventoryService inventoryService;
    private final GoodsService goodsService;
    private final ShopService shopService;
    private final InventoryMapper inventoryMapper;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<InventoryDto>> getAll() {
        List<InventoryDto> inventoryDtoList = inventoryService.getAll().stream().map(inventoryMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(inventoryDtoList);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<InventoryDto> getById(@PathVariable long id) {
        InventoryDto inventoryDto = inventoryMapper.toDto(inventoryService.readById(id));
        return ResponseEntity.ok(inventoryDto);
    }

    @GetMapping("/by-shop-id/{shopId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<InventoryDto>> getByShopId(@PathVariable long shopId) {
        List<InventoryDto> inventoryDtoList = inventoryService.findAllGoodsByShopID_Id(shopId).stream().map(inventoryMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(inventoryDtoList);
    }

    @GetMapping("/by-goods-id/{goodsId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<InventoryDto>> getByGoodsId(@PathVariable int goodsId) {
        List<InventoryDto> inventoryDtoList = inventoryService.findAllShopsByGoodsID_Id(goodsId).stream().map(inventoryMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(inventoryDtoList);
    }

    @GetMapping("/by-goods-id/{goodsId}/shop-id/{shopId}")
    @Transactional(readOnly = true)
    public ResponseEntity<InventoryDto> getByGoodsIdAndShopId(@PathVariable int goodsId, @PathVariable long shopId) {
        InventoryDto inventoryDto = inventoryMapper.toDto(inventoryService.findByGoodsIdAndShopId(goodsId, shopId));
        return ResponseEntity.ok(inventoryDto);
    }

    @GetMapping("/by-barcode/{barcode}/shop-id/{shopId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<InventoryDto>> getByBarcodeAndShopId(@PathVariable String barcode, @PathVariable long shopId) {
        List<InventoryDto> inventoryDtoList = inventoryService.findAllByGoodsInShopByBarcode(barcode, shopId).stream().map(inventoryMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(inventoryDtoList);
    }

    @GetMapping("/by-name/{name}/shop-id/{shopId}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<InventoryDto>> getByNameAndShopId(@PathVariable String name, @PathVariable long shopId) {
        List<InventoryDto> inventoryDtoList = inventoryService.findAllByGoodsInShopByNameContains(name, shopId).stream().map(inventoryMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(inventoryDtoList);
    }

    @PostMapping
    public ResponseEntity<InventoryDto> create(@RequestBody NewInventoryRequest inventoryRequest) {
        Inventory inventory = inventoryMapper.toEntity(inventoryRequest);

        inventory.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        inventory.setUpdatedOn(LocalDateTime.now());

        if (inventoryRequest.goodsId() == null || inventoryRequest.shopId() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(inventoryMapper.toDto(inventoryService.create(inventory)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InventoryDto> update(@PathVariable long id, @RequestBody NewInventoryRequest inventoryRequest) {
        Inventory existingInventory = inventoryService.readById(id);

        if (inventoryRequest.goodsId() != null) {
            Goods goods = goodsService.readById(inventoryRequest.goodsId());
            existingInventory.setGoodsID(goods);
        }

        if (inventoryRequest.shopId() != null) {
            Shop shop = shopService.readById(inventoryRequest.shopId());
            existingInventory.setShopID(shop);
        }

        Inventory updatedInventory = inventoryMapper.partialUpdate(inventoryRequest, existingInventory);
        updatedInventory.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        updatedInventory.setUpdatedOn(LocalDateTime.now());

        InventoryDto inventoryDto = inventoryMapper.toDto(inventoryService.update(updatedInventory));
        return ResponseEntity.ok(inventoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
