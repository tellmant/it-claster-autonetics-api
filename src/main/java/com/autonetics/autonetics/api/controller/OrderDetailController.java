package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.OrderDetailMapper;
import com.autonetics.autonetics.api.model.entity.Goods;
import com.autonetics.autonetics.api.model.entity.Order;
import com.autonetics.autonetics.api.model.entity.OrderDetail;
import com.autonetics.autonetics.api.model.request.NewOrderDetail;
import com.autonetics.autonetics.api.model.response.OrderDetailCreated;
import com.autonetics.autonetics.api.model.response.OrderDetailDeleted;
import com.autonetics.autonetics.api.model.response.OrderDetailDto;
import com.autonetics.autonetics.api.model.response.OrderDetailUpdated;
import com.autonetics.autonetics.api.repository.ClientRepository;
import com.autonetics.autonetics.api.repository.GoodsRepository;
import com.autonetics.autonetics.api.repository.OrderRepository;
import com.autonetics.autonetics.api.service.GoodsService;
import com.autonetics.autonetics.api.service.OrderDetailService;
import com.autonetics.autonetics.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/order-detail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final OrderService orderService;
    private final GoodsService goodsService;
    private final OrderDetailMapper orderDetailMapper;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService, OrderDetailMapper orderDetailMapper, ClientRepository clientRepository, GoodsRepository goodsRepository, OrderRepository orderRepository, OrderService orderService, GoodsService goodsService) {
        this.orderDetailService = orderDetailService;
        this.orderDetailMapper = orderDetailMapper;
        this.orderService = orderService;
        this.goodsService = goodsService;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<OrderDetailDto>> getAll() {
        List<OrderDetail> orderDetailList = orderDetailService.getAll();
        List<OrderDetailDto> orderDetailDtoList = orderDetailList.stream()
                .map(orderDetailMapper::toDto)
                .toList();

        return new ResponseEntity<>(orderDetailDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDto> readById(@PathVariable long id) {
        OrderDetail orderDetail = orderDetailService.readById(id);
        return new ResponseEntity<>(orderDetailMapper.toDto(orderDetail), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/client-barcode/{barcode}")
    public ResponseEntity<OrderDetailDto> readByClientBarcode(@PathVariable String barcode) {
        OrderDetail orderDetail = orderDetailService.findByClientBarcode(barcode);
        return new ResponseEntity<>(orderDetailMapper.toDto(orderDetail), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/goods-barcode/{barcode}")
    public ResponseEntity<OrderDetailDto> readByGoodsBarcode(@PathVariable String barcode) {
        OrderDetail orderDetail = orderDetailService.findByGoodsBarcode(barcode);
        return new ResponseEntity<>(orderDetailMapper.toDto(orderDetail), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDetailCreated> create(@RequestBody NewOrderDetail newOrderDetail) {
        Order order = orderService.readById(newOrderDetail.orderID());
        Goods goods = goodsService.readById(newOrderDetail.goodsID());

        OrderDetail orderDetail = orderDetailMapper.toEntity(newOrderDetail);

        orderDetail.setOrderID(order);
        orderDetail.setGoodsID(goods);

        orderDetail.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        orderDetail.setUpdatedOn(Instant.now());

        OrderDetail createdOrderDetail = orderDetailService.create(orderDetail);

        return new ResponseEntity<>(new OrderDetailCreated(createdOrderDetail.getId()), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderDetailUpdated> partialUpdate(@PathVariable long id, @RequestBody NewOrderDetail newOrderDetail) {
        Order order = orderService.readById(newOrderDetail.id());
        Goods goods = goodsService.readById(newOrderDetail.id());

        OrderDetail orderDetail = orderDetailMapper.toEntity(newOrderDetail);
        orderDetail.setId((int) id);
        orderDetail.setCount(newOrderDetail.count());
        orderDetail.setAmount(newOrderDetail.amount());
        orderDetail.setDiscount(newOrderDetail.discount());
        orderDetail.setAddedDateTime(newOrderDetail.addedDateTime());
        orderDetail.setPriceActual(newOrderDetail.priceActual());

        orderDetail.setOrderID(order);
        orderDetail.setGoodsID(goods);

        orderDetail.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        orderDetail.setUpdatedOn(Instant.now());

        OrderDetail updatedOrderDetail = orderDetailService.update(orderDetail);

        return new ResponseEntity<>(new OrderDetailUpdated(updatedOrderDetail.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDetailDeleted> delete(@PathVariable long id) {
        orderDetailService.delete(id);
        return new ResponseEntity<>(new OrderDetailDeleted(), HttpStatus.OK);
    }
}
