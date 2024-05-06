package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.OrderMapper;
import com.autonetics.autonetics.api.model.entity.Client;
import com.autonetics.autonetics.api.model.entity.Order;
import com.autonetics.autonetics.api.model.request.NewOrder;
import com.autonetics.autonetics.api.model.response.OrderCreated;
import com.autonetics.autonetics.api.model.response.OrderDeleted;
import com.autonetics.autonetics.api.model.response.OrderDto;
import com.autonetics.autonetics.api.model.response.OrderUpdated;
import com.autonetics.autonetics.api.service.ClientService;
import com.autonetics.autonetics.api.service.OrderService;
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
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    private final ClientService clientService;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper, ClientService clientService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.clientService = clientService;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        List<Order> orderList = orderService.getAll();
        List<OrderDto> orderDtoList = orderList.stream()
                .map(orderMapper::toDto)
                .toList();

        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> readById(@PathVariable long id) {
        Order order = orderService.readById(id);
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<OrderDto> readByBarcode(@PathVariable String barcode) {
        Order order = orderService.readByBarcode(barcode);
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/client-email/{email}")
    public ResponseEntity<OrderDto> readByClientID_Email(@PathVariable String email) {
        Order order = orderService.readByClientID_Email(email);
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/name/{name}")
    public ResponseEntity<List<OrderDto>> readByName(@PathVariable String name) {
        List<Order> orderList = orderService.readByNameContains(name);
        List<OrderDto> orderDtoList = orderList.stream()
                .map(orderMapper::toDto)
                .toList();

        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderCreated> create(@Validated @RequestBody NewOrder newOrder) {
        Client client = clientService.readById(newOrder.clientID());

        Order order = orderMapper.toEntity(newOrder);
        order.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        order.setUpdatedOn(Instant.now());
        order.setClientID(client);

        Order createdOrder = orderService.create(order);

        return new ResponseEntity<>(new OrderCreated(createdOrder.getId()), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderUpdated> update(@PathVariable long id, @Validated @RequestBody NewOrder newOrder) {
        Client client = clientService.readById(newOrder.clientID());

        Order order = orderMapper.toEntity(newOrder);
        order.setId((int) id);
        order.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        order.setUpdatedOn(Instant.now());
        order.setClientID(client);

        Order updatedOrder = orderService.update(order);

        return new ResponseEntity<>(new OrderUpdated(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDeleted> delete(@PathVariable long id) {
        orderService.delete(id);
        return new ResponseEntity<>(new OrderDeleted(), HttpStatus.OK);
    }
}
