package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.GoodsMapper;
import com.autonetics.autonetics.api.mapper.GoodsTypeMapper;
import com.autonetics.autonetics.api.model.entity.*;
import com.autonetics.autonetics.api.model.entity.Class;
import com.autonetics.autonetics.api.model.request.NewGoods;
import com.autonetics.autonetics.api.model.request.PatchGoods;
import com.autonetics.autonetics.api.model.response.GoodsCreated;
import com.autonetics.autonetics.api.model.response.GoodsDeleted;
import com.autonetics.autonetics.api.model.response.GoodsDto;
import com.autonetics.autonetics.api.model.response.GoodsUpdated;
import com.autonetics.autonetics.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/goods")
public class GoodsController {

    private final GoodsService goodsService;
    private final GoodsMapper goodsMapper;

    private final GoodsTypeService goodsTypeService;
    private final SupplierService supplierService;
    private final ClassService classService;
    private final CountryService countryService;

    @Autowired
    public GoodsController(GoodsService goodsService, GoodsMapper goodsMapper, GoodsTypeService goodsTypeService, SupplierService supplierService, ClassService classService, CountryService countryService) {
        this.goodsService = goodsService;
        this.goodsMapper = goodsMapper;
        this.goodsTypeService = goodsTypeService;
        this.supplierService = supplierService;
        this.classService = classService;
        this.countryService = countryService;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<GoodsDto>> getAll() {
        List<Goods> goodsList = goodsService.getAll();
        List<GoodsDto> goodsDtoList = goodsList.stream()
                .map(goodsMapper::toDto)
                .toList();

        return new ResponseEntity<>(goodsDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<GoodsDto> readById(@PathVariable long id) {
        Goods goods = goodsService.readById(id);
        return new ResponseEntity<>(goodsMapper.toDto(goods), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<GoodsDto> readByBarcode(@PathVariable String barcode) {
        Goods goods = goodsService.readByBarcode(barcode);
        return new ResponseEntity<>(goodsMapper.toDto(goods), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/goods-type-name/{name}")
    public ResponseEntity<List<GoodsDto>> readByGoodsTypeName(@PathVariable String name) {
        List<Goods> goods = goodsService.readByGoodsTypeId_Name(name);
        List<GoodsDto> goodsDtoList = goods.stream()
                .map(goodsMapper::toDto)
                .toList();

        return new ResponseEntity<>(goodsDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/class-name/{name}")
    public ResponseEntity<List<GoodsDto>> readByClassName(@PathVariable String name) {
        List<Goods> goods = goodsService.readByClassID_Name(name);
        List<GoodsDto> goodsDtoList = goods.stream()
                .map(goodsMapper::toDto)
                .toList();

        return new ResponseEntity<>(goodsDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/name/{name}")
    public ResponseEntity<List<GoodsDto>> readByName(@PathVariable String name) {
        List<Goods> goods = goodsService.readByName(name);
        List<GoodsDto> goodsDtoList = goods.stream()
                .map(goodsMapper::toDto)
                .toList();

        return new ResponseEntity<>(goodsDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/producer/{producer}")
    public ResponseEntity<List<GoodsDto>> readByProducer(@PathVariable String producer) {
        List<Goods> goods = goodsService.readByProducer(producer);
        List<GoodsDto> goodsDtoList = goods.stream()
                .map(goodsMapper::toDto)
                .toList();

        return new ResponseEntity<>(goodsDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GoodsCreated> create(@Validated @RequestBody NewGoods newGoods) {
        GoodsType goodsType = goodsTypeService.readById(newGoods.goodsTypeId());
        Country country = countryService.readById(Math.toIntExact(newGoods.countryID()));
        Class classEntity = classService.readById(newGoods.classID());
        Supplier supplier = supplierService.readById(newGoods.supplierID());

        Goods goods = goodsMapper.toEntity(newGoods);
        goods.setUpdatedOn(Instant.now());
        goods.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        goods.setGoodsTypeId(goodsType);
        goods.setCountryID(country);
        goods.setClassID(classEntity);
        goods.setSupplierID(supplier);

        Goods createdGoods = goodsService.create(goods);

        return new ResponseEntity<>(new GoodsCreated(createdGoods.getId()), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GoodsUpdated> update(@PathVariable long id, @Validated @RequestBody PatchGoods patchGoods) {
        GoodsType goodsType = goodsTypeService.readById(patchGoods.goodsTypeId());
        Country country = countryService.readById(Math.toIntExact(patchGoods.countryID()));
        Class classEntity = classService.readById(patchGoods.classID());
        Supplier supplier = supplierService.readById(patchGoods.supplierID());

        Goods goods = goodsMapper.toEntity(patchGoods);
        goods.setId((int) id);
        goods.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        goods.setUpdatedOn(Instant.now());
        goods.setGoodsTypeId(goodsType);
        goods.setCountryID(country);
        goods.setClassID(classEntity);
        goods.setSupplierID(supplier);

        Goods updatedGoods = goodsService.update(goods);

        return new ResponseEntity<>(new GoodsUpdated(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GoodsDeleted> delete(@PathVariable long id) {
        goodsService.delete(id);
        return new ResponseEntity<>(new GoodsDeleted((id)), HttpStatus.OK);
    }
}
