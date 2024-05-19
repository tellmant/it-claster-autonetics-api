package com.autonetics.autonetics.api.model.response;

public record DeliveryHistoryDeleted(
        String message
) {
    public DeliveryHistoryDeleted(long id) {
        this("Delivery history deleted successfully.");
    }
}
