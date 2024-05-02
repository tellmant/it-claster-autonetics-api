package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.exception.NullEntityReferenceException;
import com.autonetics.autonetics.api.model.entity.Supplier;
import com.autonetics.autonetics.api.repository.SupplierRepository;
import com.autonetics.autonetics.api.service.SupplierService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public Supplier create(Supplier supplier) {
        if (supplier != null) {
            return supplierRepository.save(supplier);
        }
        throw new NullEntityReferenceException("Supplier cannot be 'null'.");
    }

    @Override
    public Supplier readById(long id) {
        return supplierRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Supplier with id " + id + " not found.")
                );
    }

    @Override
    public Supplier update(Supplier supplier) {
        if (supplier != null) {
            return supplierRepository.save(supplier);
        }
        throw new NullEntityReferenceException("Supplier cannot be 'null'.");
    }

    @Override
    public void delete(long id) {
        Supplier supplier = readById(id);
        supplierRepository.delete(supplier);
    }

    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }
}
