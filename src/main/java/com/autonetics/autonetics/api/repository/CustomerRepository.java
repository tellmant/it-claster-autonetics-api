package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByAddress_Id(Long addressId);
    List<Customer> findAllByNameContaining(String name);
}