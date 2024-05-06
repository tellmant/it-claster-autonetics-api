package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.ShopTypeMapper;
import com.autonetics.autonetics.api.model.entity.ShopType;
import com.autonetics.autonetics.api.model.request.NewShopTypeRequest;
import com.autonetics.autonetics.api.model.response.ShopTypeDto;
import com.autonetics.autonetics.api.service.ShopTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/shop-types")
public class ShopTypeController {
    private ShopTypeService shopTypeService;
    private final ShopTypeMapper shopTypeMapper;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<ShopTypeDto>> getAll() {
        return ResponseEntity.ok(shopTypeService.getAll().stream().map(shopTypeMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopTypeDto> getById(@PathVariable long id) {
        ShopType shopType = shopTypeService.readById(id);
        if (shopType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(shopTypeMapper.toDto(shopType));
    }

    @PostMapping
    public ResponseEntity<ShopTypeDto> create(@Validated @RequestBody NewShopTypeRequest newShopTypeRequest) {
        if (newShopTypeRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ShopType shopType = shopTypeMapper.toEntity(newShopTypeRequest);
        shopType.setUpdatedOn(LocalDateTime.now());
        shopType = shopTypeService.create(shopType);
        return new ResponseEntity<>(shopTypeMapper.toDto(shopType), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ShopTypeDto> update(@PathVariable long id, @Validated @RequestBody NewShopTypeRequest newShopTypeRequest) {
        ShopType existingShopType = shopTypeService.readById(id);
        if (existingShopType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ShopType updatedShopType = shopTypeMapper.toEntity(newShopTypeRequest);
        updatedShopType.setId((int) id);
        updatedShopType.setUpdatedOn(LocalDateTime.now());
        updatedShopType = shopTypeService.update(updatedShopType);
        return ResponseEntity.ok(shopTypeMapper.toDto(updatedShopType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ShopTypeDto> delete(@PathVariable long id) {
        ShopType shopType = shopTypeService.readById(id);
        if (shopType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        shopTypeService.delete(id);
        return ResponseEntity.ok(shopTypeMapper.toDto(shopType));
    }
}
