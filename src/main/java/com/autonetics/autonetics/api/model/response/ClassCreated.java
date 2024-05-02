package com.autonetics.autonetics.api.model.response;

public record ClassCreated(
        String message,
        long id
) {
    public ClassCreated(long id) {
        this("Class added successfully.", id);
    }
}
