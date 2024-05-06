package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.Shop;
import com.autonetics.autonetics.api.repository.AddressRepository;
import com.autonetics.autonetics.api.repository.CustomerRepository;
import com.autonetics.autonetics.api.repository.ShopRepository;
import com.autonetics.autonetics.api.repository.ShopTypeRepository;
import com.autonetics.autonetics.api.service.ShopService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final ShopTypeRepository shopTypeRepository;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;


    @Override
    public Shop create(Shop shop) {
        if (shop != null) {
            return shopRepository.save(shop);
        }
        throw new NullEntityReferenceException("Shop cannot be 'null'.");
    }

    @Override
    public Shop readById(long id) {
        return shopRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Shop with id " + id + " not found.")
        );
    }

    @Override
    public Shop update(Shop shop) {
        if (shop != null) {
            return shopRepository.save(shop);
        }
        throw new NullEntityReferenceException("Shop cannot be 'null'.");
    }

    @Override
    public void delete(long id) {
        Shop shop = readById(id);
        shopRepository.delete(shop);
    }

    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    @Override
    public List<Shop> getAllByAddressId(Long addressId) {
        if (addressRepository.findById(addressId).isEmpty()) {
            throw new EntityNotFoundException("Address with id " + addressId + " not found.");
        }
        return shopRepository.findAllByAddress_Id(addressId);
    }

    @Override
    public List<Shop> getAllByCustomerId(Long customerId) {
        if (customerRepository.findById(customerId).isEmpty()) {
            throw new EntityNotFoundException("Customer with id " + customerId + " not found.");
        }
        return shopRepository.findAllByCustomer_Id(customerId);
    }

    @Override
    public List<Shop> getAllByShopTypeId(Long shopTypeId) {
        if (shopTypeRepository.findById(shopTypeId).isEmpty()) {
            throw new EntityNotFoundException("ShopType with id " + shopTypeId + " not found.");
        }
        return shopRepository.findAllByShopType_Id(shopTypeId);
    }

    @Override
    public List<Shop> getAllByNameContaining(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullEntityReferenceException("Name cannot be 'null' or empty.");
        }
        return shopRepository.findAllByNameContaining(name);
    }
}
