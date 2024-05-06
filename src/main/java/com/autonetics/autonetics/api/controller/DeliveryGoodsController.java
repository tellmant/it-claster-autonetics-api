package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.DeliveryGoodsMapper;
import com.autonetics.autonetics.api.model.entity.DeliveryGoods;
import com.autonetics.autonetics.api.model.entity.Goods;
import com.autonetics.autonetics.api.model.entity.Supplier;
import com.autonetics.autonetics.api.model.request.NewDeliveryGoods;
import com.autonetics.autonetics.api.model.response.*;
import com.autonetics.autonetics.api.service.DeliveryGoodsService;
import com.autonetics.autonetics.api.service.GoodsService;
import com.autonetics.autonetics.api.service.SupplierService;
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
@RequestMapping("api/delivery-goods")
public class DeliveryGoodsController {

    private final DeliveryGoodsService deliveryGoodsService;
    private final DeliveryGoodsMapper deliveryGoodsMapper;

    private final SupplierService supplierService;
    private final GoodsService goodsService;


    @Autowired
    public DeliveryGoodsController(DeliveryGoodsService deliveryGoodsService, DeliveryGoodsMapper deliveryGoodsMapper, SupplierService supplierService, GoodsService goodsService) {
        this.deliveryGoodsService = deliveryGoodsService;
        this.deliveryGoodsMapper = deliveryGoodsMapper;
        this.supplierService = supplierService;
        this.goodsService = goodsService;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<DeliveryGoodsDto>> getAll() {
        List<DeliveryGoods> deliveryGoodsList = deliveryGoodsService.getAll();
        List<DeliveryGoodsDto> deliveryGoodsDtoList = deliveryGoodsList.stream()
                .map(deliveryGoodsMapper::toDto)
                .toList();

        return new ResponseEntity<>(deliveryGoodsDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryGoodsDto> readById(@PathVariable long id) {
        DeliveryGoods deliveryGoods = deliveryGoodsService.readById(id);
        return new ResponseEntity<>(deliveryGoodsMapper.toDto(deliveryGoods), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<DeliveryGoodsDto> readByBarcode(@PathVariable String barcode) {
        DeliveryGoods deliveryGoods = deliveryGoodsService.readByBarcode(barcode);
        return new ResponseEntity<>(deliveryGoodsMapper.toDto(deliveryGoods), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/number/{number}")
    public ResponseEntity<DeliveryGoodsDto> readByNumber(@PathVariable int number) {
        DeliveryGoods deliveryGoods = deliveryGoodsService.readByNumber(number);
        return new ResponseEntity<>(deliveryGoodsMapper.toDto(deliveryGoods), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/supplier-name/{name}")
    public ResponseEntity<List<DeliveryGoodsDto>> readBySupplierName(@PathVariable String name) {
        List<DeliveryGoods> deliveryGoodsList = deliveryGoodsService.readBySupplierNameContains(name);
        List<DeliveryGoodsDto> deliveryGoodsDtoList = deliveryGoodsList.stream()
                .map(deliveryGoodsMapper::toDto)
                .toList();

        return new ResponseEntity<>(deliveryGoodsDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/goods-type-name/{name}")
    public ResponseEntity<List<DeliveryGoodsDto>> readByGoodsTypeName(@PathVariable String name) {
        List<DeliveryGoods> deliveryGoodsList = deliveryGoodsService.readByGoodsTypeNameContains(name);
        List<DeliveryGoodsDto> deliveryGoodsDtoList = deliveryGoodsList.stream()
                .map(deliveryGoodsMapper::toDto)
                .toList();

        return new ResponseEntity<>(deliveryGoodsDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/goods-barcode/{barcode}")
    public ResponseEntity<DeliveryGoodsDto> readByGoodsBarcode(@PathVariable String barcode) {
        DeliveryGoods deliveryGoods = deliveryGoodsService.readByGoodsBarcode(barcode);
        return new ResponseEntity<>(deliveryGoodsMapper.toDto(deliveryGoods), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/goods-name/{name}")
    public ResponseEntity<List<DeliveryGoodsDto>> readByGoodsName(@PathVariable String name) {
        List<DeliveryGoods> deliveryGoodsList = deliveryGoodsService.readByGoodsNameContains(name);
        List<DeliveryGoodsDto> deliveryGoodsDtoList = deliveryGoodsList.stream()
                .map(deliveryGoodsMapper::toDto)
                .toList();

        return new ResponseEntity<>(deliveryGoodsDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/goods-producer/{producer}")
    public ResponseEntity<List<DeliveryGoodsDto>> readByGoodsProducer(@PathVariable String producer) {
        List<DeliveryGoods> deliveryGoodsList = deliveryGoodsService.readByGoodsProducerContains(producer);
        List<DeliveryGoodsDto> deliveryGoodsDtoList = deliveryGoodsList.stream()
                .map(deliveryGoodsMapper::toDto)
                .toList();

        return new ResponseEntity<>(deliveryGoodsDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/details")
    public ResponseEntity<List<DeliveryGoodsDetailDto>> getAllWithDetails() {
        List<DeliveryGoods> deliveryGoodsList = deliveryGoodsService.getAll();
        List<DeliveryGoodsDetailDto> deliveryGoodsDetailDtoList = deliveryGoodsList.stream()
                .map(deliveryGoodsMapper::toDtoWithDetails)
                .toList();

        return new ResponseEntity<>(deliveryGoodsDetailDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/details/{id}")
    public ResponseEntity<DeliveryGoodsDetailDto> readByIdWithDetails(@PathVariable long id) {
        DeliveryGoods deliveryGoods = deliveryGoodsService.readById(id);
        return new ResponseEntity<>(deliveryGoodsMapper.toDtoWithDetails(deliveryGoods), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/details/barcode/{barcode}")
    public ResponseEntity<DeliveryGoodsDetailDto> readByBarcodeWithDetails(@PathVariable String barcode) {
        DeliveryGoods deliveryGoods = deliveryGoodsService.readByBarcode(barcode);
        return new ResponseEntity<>(deliveryGoodsMapper.toDtoWithDetails(deliveryGoods), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/details/number/{number}")
    public ResponseEntity<DeliveryGoodsDetailDto> readByNumberWithDetails(@PathVariable int number) {
        DeliveryGoods deliveryGoods = deliveryGoodsService.readByNumber(number);
        return new ResponseEntity<>(deliveryGoodsMapper.toDtoWithDetails(deliveryGoods), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/details/supplier-name/{name}")
    public ResponseEntity<List<DeliveryGoodsDetailDto>> readBySupplierNameWithDetails(@PathVariable String name) {
        List<DeliveryGoods> deliveryGoodsList = deliveryGoodsService.readBySupplierNameContains(name);
        List<DeliveryGoodsDetailDto> deliveryGoodsDetailDtoList = deliveryGoodsList.stream()
                .map(deliveryGoodsMapper::toDtoWithDetails)
                .toList();

        return new ResponseEntity<>(deliveryGoodsDetailDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DeliveryGoodsCreated> create(@Validated @RequestBody NewDeliveryGoods newDeliveryGoods) {
        Goods goods = goodsService.readById(newDeliveryGoods.goodsID());
        Supplier supplier = supplierService.readById(newDeliveryGoods.supplierID());

        DeliveryGoods deliveryGoods = deliveryGoodsMapper.toEntity(newDeliveryGoods);
        deliveryGoods.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        deliveryGoods.setUpdatedOn(Instant.now());
        deliveryGoods.setGoodsID(goods);
        deliveryGoods.setSupplierID(supplier);

        DeliveryGoods createdDeliveryGoods = deliveryGoodsService.create(deliveryGoods);

        return new ResponseEntity<>(new DeliveryGoodsCreated(createdDeliveryGoods.getId()), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DeliveryGoodsUpdated> update(@PathVariable long id, @Validated @RequestBody NewDeliveryGoods newDeliveryGoods) {
        Goods goods = goodsService.readById(newDeliveryGoods.goodsID());
        Supplier supplier = supplierService.readById(newDeliveryGoods.supplierID());

        DeliveryGoods deliveryGoods = deliveryGoodsMapper.toEntity(newDeliveryGoods);
        deliveryGoods.setId((int) id);
        deliveryGoods.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        deliveryGoods.setUpdatedOn(Instant.now());
        deliveryGoods.setGoodsID(goods);
        deliveryGoods.setSupplierID(supplier);

        DeliveryGoods updatedDeliveryGoods = deliveryGoodsService.update(deliveryGoods);

        return new ResponseEntity<>(new DeliveryGoodsUpdated(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeliveryGoodsDeleted> delete(@PathVariable long id) {
        deliveryGoodsService.delete(id);
        return new ResponseEntity<>(new DeliveryGoodsDeleted(), HttpStatus.OK);
    }
}
