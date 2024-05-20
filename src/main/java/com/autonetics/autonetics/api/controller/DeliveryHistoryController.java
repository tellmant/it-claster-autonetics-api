package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.DeliveryHistoryMapper;
import com.autonetics.autonetics.api.model.entity.DeliveryGoods;
import com.autonetics.autonetics.api.model.entity.DeliveryHistory;
import com.autonetics.autonetics.api.model.entity.Goods;
import com.autonetics.autonetics.api.model.entity.Shop;
import com.autonetics.autonetics.api.model.response.DeliveryHistoryDto;
import com.autonetics.autonetics.api.service.DeliveryGoodsService;
import com.autonetics.autonetics.api.service.DeliveryHistoryService;
import com.autonetics.autonetics.api.service.GoodsService;
import com.autonetics.autonetics.api.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/delivery-history")
public class DeliveryHistoryController {

    private final DeliveryHistoryService deliveryHistoryService;
    private final DeliveryHistoryMapper deliveryHistoryMapper;

    private final GoodsService goodsService;
    private final ShopService shopService;
    private final DeliveryGoodsService deliveryGoodsService;

    public DeliveryHistoryController(DeliveryHistoryService deliveryHistoryService, DeliveryHistoryMapper deliveryHistoryMapper, GoodsService goodsService, ShopService shopService, DeliveryGoodsService deliveryGoodsService) {
        this.deliveryHistoryService = deliveryHistoryService;
        this.deliveryHistoryMapper = deliveryHistoryMapper;
        this.goodsService = goodsService;
        this.shopService = shopService;
        this.deliveryGoodsService = deliveryGoodsService;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<DeliveryHistoryDto>> getAll() {
        List<DeliveryHistory> deliveryHistoryList = deliveryHistoryService.getAll();
        List<DeliveryHistoryDto> deliveryHistoryDtoList = deliveryHistoryList.stream()
                .map(deliveryHistoryMapper::toDto)
                .toList();

        return new ResponseEntity<>(deliveryHistoryDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryHistoryDto> readById(@PathVariable long id) {
        DeliveryHistory deliveryHistory = deliveryHistoryService.readById(id);
        return new ResponseEntity<>(deliveryHistoryMapper.toDto(deliveryHistory), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/delivery-barcode/{barcode}")
    public ResponseEntity<List<DeliveryHistoryDto>> readByDeliveryId(@PathVariable String barcode) {
        DeliveryHistory deliveryHistoryList = deliveryHistoryService.readByDeliveryGoodsid_Barcode(barcode);
        List<DeliveryHistoryDto> deliveryHistoryDtoList = List.of(deliveryHistoryMapper.toDto(deliveryHistoryList));

        return new ResponseEntity<>(deliveryHistoryDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/goods-barcode/{barcode}")
    public ResponseEntity<List<DeliveryHistoryDto>> readByGoodsId(@PathVariable String barcode) {
        List<DeliveryHistory> deliveryHistoryList = Collections.singletonList(deliveryHistoryService.readByGoodsID_Barcode(barcode));
        List<DeliveryHistoryDto> deliveryHistoryDtoList = deliveryHistoryList.stream()
                .map(deliveryHistoryMapper::toDto)
                .toList();

        return new ResponseEntity<>(deliveryHistoryDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DeliveryHistoryDto> create(@RequestBody DeliveryHistoryDto deliveryHistoryDto) {
        DeliveryHistory deliveryHistory = deliveryHistoryMapper.toEntity(deliveryHistoryDto);
        Goods goods = goodsService.readById(deliveryHistoryDto.goodsID().id());
        deliveryHistory.setGoodsID(goods);

        DeliveryGoods deliveryGoods = deliveryGoodsService.readById(deliveryHistoryDto.deliveryGoodsid().id());
        deliveryHistory.setDeliveryGoodsid(deliveryGoods);

        Shop shop = shopService.readById(deliveryHistoryDto.shopID().id());
        deliveryHistory.setShopID(shop);

        deliveryHistory = deliveryHistoryService.create(deliveryHistory);

        return new ResponseEntity<>(deliveryHistoryMapper.toDto(deliveryHistory), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DeliveryHistoryDto> update(@PathVariable long id, @RequestBody DeliveryHistoryDto deliveryHistoryDto) {
        DeliveryHistory deliveryHistory = deliveryHistoryService.readById(id);
        deliveryHistory = deliveryHistoryMapper.partialUpdate(deliveryHistoryDto, deliveryHistory);

        Goods goods = goodsService.readById(deliveryHistoryDto.goodsID().id());
        deliveryHistory.setGoodsID(goods);

        DeliveryGoods deliveryGoods = deliveryGoodsService.readById(deliveryHistoryDto.deliveryGoodsid().id());
        deliveryHistory.setDeliveryGoodsid(deliveryGoods);

        Shop shop = shopService.readById(deliveryHistoryDto.shopID().id());
        deliveryHistory.setShopID(shop);

        deliveryHistory = deliveryHistoryService.update(deliveryHistory);

        return new ResponseEntity<>(deliveryHistoryMapper.toDto(deliveryHistory), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        deliveryHistoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
