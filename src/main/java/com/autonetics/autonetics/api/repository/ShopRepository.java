package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findAllByAddress_Id(Long addressId);

    List<Shop> findAllByCustomer_Id(Long customerId);

    List<Shop> findAllByShopType_Id(Long shopTypeId);

    List<Shop> findAllByNameContaining(String name);

    @Query(value = "SELECT s FROM Shop s JOIN s.address a WHERE " +
            "(6371 * acos(cos(radians(:latitude))" +
            " * cos(radians(a.latitude)) * cos(radians(a.longitude)" +
            " - radians(:longitude)) + sin(radians(:latitude))" +
            " * sin(radians(a.latitude)))) < 0.5")
    List<Shop> findAllIn500Meters(@Param("latitude") BigDecimal latitude, @Param("longitude") BigDecimal longitude);
}