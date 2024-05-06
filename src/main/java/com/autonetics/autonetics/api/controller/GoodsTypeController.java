package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.GoodsTypeMapper;
import com.autonetics.autonetics.api.model.entity.GoodsType;
import com.autonetics.autonetics.api.model.request.NewGoodsType;
import com.autonetics.autonetics.api.model.request.PatchGoodsType;
import com.autonetics.autonetics.api.model.response.GoodsTypeCreated;
import com.autonetics.autonetics.api.model.response.GoodsTypeDeleted;
import com.autonetics.autonetics.api.model.response.GoodsTypeDto;
import com.autonetics.autonetics.api.model.response.GoodsTypeUpdated;
import com.autonetics.autonetics.api.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/goods-type")
public class GoodsTypeController {

    private final GoodsTypeService goodsTypeService;

    private final GoodsTypeMapper goodsTypeMapper;

    @Autowired
    public GoodsTypeController(GoodsTypeService goodsTypeService, GoodsTypeMapper goodsTypeMapper) {
        this.goodsTypeService = goodsTypeService;
        this.goodsTypeMapper = goodsTypeMapper;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<GoodsTypeDto>> getAll() {
        List<GoodsType> goodsTypeList = goodsTypeService.getAll();
        List<GoodsTypeDto> goodsTypeDtoList = goodsTypeList.stream()
                .map(goodsTypeMapper::toDto)
                .toList();

        return new ResponseEntity<>(goodsTypeDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<GoodsTypeDto> readById(@PathVariable long id) {
        GoodsType goodsType = goodsTypeService.readById(id);
        return new ResponseEntity<>(goodsTypeMapper.toDto(goodsType), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GoodsTypeCreated> create(@Validated @RequestBody NewGoodsType newGoodsType) {
        GoodsType goodsType = new GoodsType();
        goodsType.setName(newGoodsType.name());
        goodsType.setUpdatedBy(newGoodsType.updatedBy());
        goodsType.setUpdatedOn(Instant.now());

        GoodsType createdGoodsType = goodsTypeService.create(goodsType);

        return new ResponseEntity<>(new GoodsTypeCreated(createdGoodsType.getId()), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GoodsTypeUpdated> update(@PathVariable long id, @Validated @RequestBody PatchGoodsType patchGoodsType) {
        GoodsType goodsType = new GoodsType();
        goodsType.setId((int) id);
        goodsType.setName(patchGoodsType.name());
        goodsType.setUpdatedBy(patchGoodsType.updatedBy());
        goodsType.setUpdatedOn(Instant.now());

        GoodsType updatedGoodsType = goodsTypeService.update(goodsType);

        return new ResponseEntity<>(new GoodsTypeUpdated(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GoodsTypeDeleted> delete(@PathVariable long id) {
        goodsTypeService.delete(id);
        return new ResponseEntity<>(new GoodsTypeDeleted(id), HttpStatus.OK);
    }
}
