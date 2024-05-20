package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.OrderDetail;
import com.autonetics.autonetics.api.repository.OrderDetailRepository;
import com.autonetics.autonetics.api.service.OrderDetailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        if (orderDetail != null) {
            return orderDetailRepository.save(orderDetail);
        }
        throw new NullEntityReferenceException("OrderDetail cannot be 'null'.");
    }

    @Override
    public OrderDetail readById(long id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("OrderDetail with id " + id + " not found.")
                );
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        if (orderDetail != null) {
            return orderDetailRepository.save(orderDetail);
        }
        throw new NullEntityReferenceException("OrderDetail cannot be 'null'.");
    }

    @Override
    public void delete(long id) {
        OrderDetail orderDetail = readById(id);
        orderDetailRepository.delete(orderDetail);
    }

    @Override
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail findByClientBarcode(String barcode) {
        return orderDetailRepository.findByOrderID_Barcode(barcode).orElseThrow(
                () -> new EntityNotFoundException("OrderDetail with order barcode " + barcode + " not found.")
        );
    }

    @Override
    public OrderDetail findByGoodsBarcode(String barcode) {
        return orderDetailRepository.findByGoodsID_Barcode(barcode).orElseThrow(
                () -> new EntityNotFoundException("OrderDetail with goods barcode " + barcode + " not found.")
        );
    }

    @Override
    public List<OrderDetail> findByClientEmail(String email) {
        return orderDetailRepository.findByOrderID_ClientID_Email(email).orElseThrow(
                () -> new EntityNotFoundException("OrderDetail with client email " + email + " not found.")
        );
    }
}
