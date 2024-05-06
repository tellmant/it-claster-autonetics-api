package com.autonetics.autonetics.api.model.response;

public record SupplierDeleted(
        String message,
        long id
) {
    public SupplierDeleted(long id) {
        this("Supplier deleted successfully.", id);
    }
}
