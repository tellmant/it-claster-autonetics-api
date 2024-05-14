package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByLatitudeAndLongitude (BigDecimal latitude, BigDecimal longitude);
    List<Address> findAllByStreet_Id(Long streetId);

    @Query(value = "SELECT l FROM Address l WHERE " +
            "(6371 * acos(cos(radians(:latitude))" +
            " * cos(radians(l.latitude)) * cos(radians(l.longitude)" +
            " - radians(:longitude)) + sin(radians(:latitude))" +
            " * sin(radians(l.latitude)))) < 0.5")
    List<Address> findLocationsWithin100Meters(@Param("latitude") BigDecimal latitude, @Param("longitude") BigDecimal longitude);
}