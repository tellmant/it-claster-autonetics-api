package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Optional<OrderDetail> findByOrderID_Barcode(String barcode);

    Optional<OrderDetail> findByGoodsID_Barcode(String barcode);
}