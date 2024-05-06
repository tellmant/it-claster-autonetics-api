package com.autonetics.autonetics.api.model.response;

public record SupplierCreated(
        String message,
        long id
) {
    public SupplierCreated(long id) {
        this("Supplier added successfully.", id);
    }
}
