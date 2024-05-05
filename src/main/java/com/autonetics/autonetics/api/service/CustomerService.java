package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);

    Customer readById(long id);

    Customer update(Customer customer);

    void delete(long id);

    List<Customer> getAll();
    List<Customer> getAllByName(String name);
    Customer findByAddressId(Long addressId);
}
