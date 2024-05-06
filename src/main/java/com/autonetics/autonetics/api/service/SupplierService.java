package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier create(Supplier supplier);

    Supplier readById(long id);

    Supplier update(Supplier supplier);

    void delete(long id);

    List<Supplier> getAll();
}
