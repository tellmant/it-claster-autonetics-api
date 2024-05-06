package com.autonetics.autonetics.api.model.response;

public record OrderCreated(
        String message,
        long id
) {
    public OrderCreated(long id) {
        this("Order added successfully.", id);
    }
}
