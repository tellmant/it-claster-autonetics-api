package com.autonetics.autonetics.api.model.response;

public record OrderDeleted(
        String message
) {
    public OrderDeleted() {
        this("Order deleted successfully.");
    }
}
