package com.autonetics.autonetics.api.model.response;

public record ClassUpdated(
        String message
) {
    public ClassUpdated() {
        this("Class updated successfully.");
    }
}
