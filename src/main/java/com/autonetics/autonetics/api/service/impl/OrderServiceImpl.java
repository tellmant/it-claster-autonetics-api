package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.Order;
import com.autonetics.autonetics.api.repository.OrderRepository;
import com.autonetics.autonetics.api.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        if (order != null) {
            return orderRepository.save(order);
        }
        throw new NullEntityReferenceException("Order cannot be 'null'.");
    }

    @Override
    public Order readById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Order with id " + id + " not found.")
                );
    }

    @Override
    public Order update(Order order) {
        if (order != null) {
            return orderRepository.save(order);
        }
        throw new NullEntityReferenceException("Order cannot be 'null'.");
    }

    @Override
    public void delete(long id) {
        Order order = readById(id);
        orderRepository.delete(order);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order readByBarcode(String barcode) {
        return orderRepository.findByBarcode(barcode)
                .orElseThrow(
                        () -> new EntityNotFoundException("Order with barcode " + barcode + " not found.")
                );
    }

    @Override
    public Order readByClientID_Email(String email) {
        return orderRepository.findByClientID_Email(email)
                .orElseThrow(
                        () -> new EntityNotFoundException("Order with client email " + email + " not found.")
                );
    }

    @Override
    public List<Order> readByNameContains(String name) {
        return orderRepository.findByNameContains(name)
                .orElseThrow(
                        () -> new EntityNotFoundException("Order with name " + name + " not found.")
                );
    }
}
