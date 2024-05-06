package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.DeliveryGoods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryGoodsRepository extends JpaRepository<DeliveryGoods, Long> {

    Optional<DeliveryGoods> findByBarcode(String barcode);

    Optional<DeliveryGoods> findByNumber(Integer number);

    Optional<List<DeliveryGoods>> findBySupplierID_NameContains(String name);

    Optional<List<DeliveryGoods>> findByGoodsID_GoodsTypeId_NameContains(String name);

    Optional<DeliveryGoods> findByGoodsID_Barcode(String barcode);

    Optional<List<DeliveryGoods>> findByGoodsID_NameContains(String name);

    Optional<List<DeliveryGoods>> findByGoodsID_ProducerContains(String producer);
}
