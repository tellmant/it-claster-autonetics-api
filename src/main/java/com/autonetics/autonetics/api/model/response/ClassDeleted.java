package com.autonetics.autonetics.api.model.response;

public record ClassDeleted(
        String message,
        long id
) {
    public ClassDeleted(long id) {
        this("Class deleted successfully.", id);
    }
}
