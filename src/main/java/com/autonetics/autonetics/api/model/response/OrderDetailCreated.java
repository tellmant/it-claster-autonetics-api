package com.autonetics.autonetics.api.model.response;

public record OrderDetailCreated(
        String message,
        long id
) {
    public OrderDetailCreated(long id) {
        this("OrderDetail created successfully.", id);
    }
}
