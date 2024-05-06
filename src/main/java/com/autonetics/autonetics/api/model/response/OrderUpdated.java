package com.autonetics.autonetics.api.model.response;

public record OrderUpdated(
        String message,
        long id
) {
    public OrderUpdated(long id) {
        this("Order updated successfully.", id);
    }
}
