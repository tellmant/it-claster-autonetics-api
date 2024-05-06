package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByLatitudeAndLongitude (BigDecimal latitude, BigDecimal longitude);
    List<Address> findAllByStreet_Id(Long streetId);
}