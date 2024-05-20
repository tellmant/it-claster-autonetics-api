package com.autonetics.autonetics.api.model.response;

public record DeliveryHistoryUpdated(
        String message,
        long id
) {
    public DeliveryHistoryUpdated(long id) {
        this("Delivery history updated successfully", id);
    }
}
