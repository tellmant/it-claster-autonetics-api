package com.autonetics.autonetics.api.model.response;

public record SupplierUpdated (
        String message,
        long id
) {
    public SupplierUpdated(long id) {
        this("Supplier updated successfully.", id);
    }
}
