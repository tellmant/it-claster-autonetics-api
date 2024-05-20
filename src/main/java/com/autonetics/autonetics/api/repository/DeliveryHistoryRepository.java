package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.DeliveryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory, Long> {
  Optional<DeliveryHistory> findByGoodsID_Barcode(String barcode);

  Optional<DeliveryHistory> findByDeliveryGoodsid_Barcode(String barcode);
}