package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.GoodsMapper;
import com.autonetics.autonetics.api.mapper.GoodsTypeMapper;
import com.autonetics.autonetics.api.model.entity.Goods;
import com.autonetics.autonetics.api.model.request.NewGoods;
import com.autonetics.autonetics.api.model.request.PatchGoods;
import com.autonetics.autonetics.api.model.response.GoodsCreated;
import com.autonetics.autonetics.api.model.response.GoodsDeleted;
import com.autonetics.autonetics.api.model.response.GoodsDto;
import com.autonetics.autonetics.api.model.response.GoodsUpdated;
import com.autonetics.autonetics.api.service.GoodsService;
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
    private final GoodsTypeMapper goodsTypeMapper;

    @Autowired
    public GoodsController(GoodsService goodsService, GoodsMapper goodsMapper, GoodsTypeMapper goodsTypeMapper) {
        this.goodsService = goodsService;
        this.goodsMapper = goodsMapper;
        this.goodsTypeMapper = goodsTypeMapper;
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

    @PostMapping
    public ResponseEntity<GoodsCreated> create(@Validated @RequestBody NewGoods newGoods) {
        Goods goods = goodsMapper.toEntity(newGoods);
        goods.setUpdatedOn(Instant.now());
        goods.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        Goods createdGoods = goodsService.create(goods);

        return new ResponseEntity<>(new GoodsCreated(createdGoods.getId()), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GoodsUpdated> update(@PathVariable long id, @Validated @RequestBody PatchGoods patchGoods) {
        Goods goods = goodsMapper.toEntity(patchGoods);
        goods.setId((int) id);
        goods.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        goods.setUpdatedOn(Instant.now());

        Goods updatedGoods = goodsService.update(goods);

        return new ResponseEntity<>(new GoodsUpdated(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GoodsDeleted> delete(@PathVariable long id) {
        goodsService.delete(id);
        return new ResponseEntity<>(new GoodsDeleted((id)), HttpStatus.OK);
    }
}
