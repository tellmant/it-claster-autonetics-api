package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.Customer;
import com.autonetics.autonetics.api.repository.AddressRepository;
import com.autonetics.autonetics.api.repository.CustomerRepository;
import com.autonetics.autonetics.api.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Override
    public Customer create(Customer customer) {
        if (customer != null) {
            return customerRepository.save(customer);
        }
        throw new NullEntityReferenceException("Customer cannot be 'null'.");
    }

    @Override
    public Customer readById(long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Customer with id " + id + " not found."));
    }

    @Override
    public Customer update(Customer customer) {
        if (customer != null) {
            return customerRepository.save(customer);
        }
        throw new NullEntityReferenceException("Customer cannot be 'null'.");
    }

    @Override
    public void delete(long id) {
        Customer customer = readById(id);
        customerRepository.delete(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> getAllByName(String name) {
        return customerRepository.findAllByNameContaining(name);
    }

    @Override
    public Customer findByAddressId(Long addressId) {
        if (addressRepository.findById(addressId).isEmpty()) {
            throw new EntityNotFoundException("Address with id " + addressId + " not found.");
        }
        return customerRepository.findCustomerByAddress_Id(addressId);
    }
}
