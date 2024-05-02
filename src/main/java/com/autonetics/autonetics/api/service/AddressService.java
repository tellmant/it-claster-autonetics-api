package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Address;

import java.math.BigDecimal;
import java.util.List;

public interface AddressService {
    Address readById(long id);
    Address create(Address address);
    Address create(Address address, Long settlementId, Long streetId);
    Address update(Address address);
    void delete(long id);
    Address findByLatitudeAndLongitude (BigDecimal latitude, BigDecimal longitude);
    List<Address> findAllByStreet_Id(Long streetId);
    List<Address> getAll();
}
