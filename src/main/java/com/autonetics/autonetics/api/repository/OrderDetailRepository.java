package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Optional<OrderDetail> findByOrderID_Barcode(String barcode);

    Optional<OrderDetail> findByGoodsID_Barcode(String barcode);

    Optional<List<OrderDetail>> findByOrderID_ClientID_Email(String email);

}