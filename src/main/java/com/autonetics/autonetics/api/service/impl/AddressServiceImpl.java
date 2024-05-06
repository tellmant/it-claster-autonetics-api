package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.model.entity.Address;
import com.autonetics.autonetics.api.model.entity.Settlement;
import com.autonetics.autonetics.api.model.entity.Street;
import com.autonetics.autonetics.api.repository.AddressRepository;
import com.autonetics.autonetics.api.repository.SettlementRepository;
import com.autonetics.autonetics.api.repository.StreetRepository;
import com.autonetics.autonetics.api.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final StreetRepository streetRepository;
    private final SettlementRepository settlementRepository;

    @Override
    public Address readById(long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Address with id " + id + " not found"));
    }

    @Override
    public Address create(Address address) {
        if (address != null) {
            return addressRepository.save(address);
        }
        throw new EntityNotFoundException("Address cannot be 'null'");
    }
    @Override
    public Address create(Address address, Long settlementId, Long streetId) {
        Settlement settlement = settlementRepository.findById(settlementId)
                .orElseThrow(() -> new EntityNotFoundException("Settlement with id " + settlementId + " not found."));
        Street street = streetRepository.findById(streetId)
                .orElseThrow(() -> new EntityNotFoundException("Street with id " + streetId + " not found."));

        address.setSettlement(settlement);
        address.setStreet(street);

        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address) {
        if (address != null) {
            return addressRepository.save(address);
        }
        throw new EntityNotFoundException("Address cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        Address address = readById(id);
        addressRepository.delete(address);
    }

    @Override
    public Address findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude) {
        return addressRepository.findByLatitudeAndLongitude(latitude, longitude)
                .orElseThrow(
                        () -> new EntityNotFoundException("Address with latitude " + latitude + " and longitude " + longitude + " not found.")
                );
    }

    @Override
    public List<Address> findAllByStreet_Id(Long streetId) {
        if (streetRepository.findById(streetId).isPresent()) {
            return addressRepository.findAllByStreet_Id(streetId);
        }
        throw new EntityNotFoundException("Street with id " + streetId + " not found.");
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
