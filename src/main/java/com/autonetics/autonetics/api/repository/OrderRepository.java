package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByBarcode(String barcode);

    Optional<List<Order>> findByClientID_Email(String email);

    Optional<List<Order>> findByNameContains(String name);
}