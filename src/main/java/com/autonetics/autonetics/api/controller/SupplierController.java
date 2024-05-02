package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.SupplierMapper;
import com.autonetics.autonetics.api.model.entity.Supplier;
import com.autonetics.autonetics.api.model.request.NewSupplierRequest;
import com.autonetics.autonetics.api.model.response.SupplierCreated;
import com.autonetics.autonetics.api.model.response.SupplierDeleted;
import com.autonetics.autonetics.api.model.response.SupplierDto;
import com.autonetics.autonetics.api.model.response.SupplierUpdated;
import com.autonetics.autonetics.api.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/supplier")
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    public SupplierController(SupplierService supplierService, SupplierMapper supplierMapper) {
        this.supplierService = supplierService;
        this.supplierMapper = supplierMapper;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAll() {
        List<Supplier> supplierList = supplierService.getAll();
        List<SupplierDto> supplierDtoList = supplierList.stream()
                .map(supplierMapper::toDto)
                .toList();

        return new ResponseEntity<>(supplierDtoList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> readById(@PathVariable long id) {
        Supplier supplier = supplierService.readById(id);
        return new ResponseEntity<>(supplierMapper.toDto(supplier), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SupplierCreated> create(@Validated @RequestBody NewSupplierRequest newSupplierRequest) {
        Supplier supplier = supplierMapper.toEntity(newSupplierRequest);
        supplier.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        supplier.setUpdatedOn(Instant.now());

        Supplier createdSupplier = supplierService.create(supplier);

        return new ResponseEntity<>(new SupplierCreated(createdSupplier.getId()), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SupplierUpdated> update(@PathVariable long id, @Validated @RequestBody NewSupplierRequest newSupplierRequest) {
        Supplier supplier = supplierMapper.toEntity(newSupplierRequest);
        supplier.setId((int) id);
        supplier.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        supplier.setUpdatedOn(Instant.now());

        Supplier updatedSupplier = supplierService.update(supplier);

        return new ResponseEntity<>(new SupplierUpdated(updatedSupplier.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierDeleted> delete(@PathVariable long id) {
        supplierService.delete(id);
        return new ResponseEntity<>(new SupplierDeleted(id), HttpStatus.OK);
    }
}
