package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.mapper.CustomerMapper;
import com.autonetics.autonetics.api.model.entity.Address;
import com.autonetics.autonetics.api.model.entity.Customer;
import com.autonetics.autonetics.api.model.request.NewCustomerRequest;
import com.autonetics.autonetics.api.model.response.CustomerDto;
import com.autonetics.autonetics.api.service.AddressService;
import com.autonetics.autonetics.api.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final AddressService addressService;
    private final CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAll().stream()
                .map(customerMapper::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<CustomerDto>> getCustomersByName(@PathVariable String name) {
        return ResponseEntity.ok(customerService.getAllByName(name).stream()
                .map(customerMapper::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/by-addressId/{addressId}")
    public ResponseEntity<CustomerDto> getCustomerByAddressId(@PathVariable Long addressId) {
        return ResponseEntity.ok(customerMapper.toDto(customerService.findByAddressId(addressId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerMapper.toDto(customerService.readById(id)));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody NewCustomerRequest newCustomer) {
        Customer customer = customerMapper.toEntity(newCustomer);
        customer.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        customer.setUpdatedOn(LocalDateTime.now());

        if (newCustomer.website() == null || newCustomer.website().isEmpty()){
            customer.setWebsite("NO WEBSITE");
        } else {
            customer.setWebsite(newCustomer.website());
        }

        Address address = addressService.readById(newCustomer.addressId());
        customer.setAddress(address);

        return ResponseEntity.ok(customerMapper.toDto(customerService.create(customer)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody NewCustomerRequest newCustomer) {
        Customer customer = customerService.readById(id);
        customerMapper.partialUpdate(newCustomer, customer);
        customer.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        customer.setUpdatedOn(LocalDateTime.now());
        if (newCustomer.onlineShoping() == null || !newCustomer.onlineShoping()){
            customer.setWebsite("NO WEBSITE");
        }
        return ResponseEntity.ok(customerMapper.toDto(customerService.update(customer)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
