package com.autonetics.autonetics.api.model.response;

public record OrderDetailDeleted(
        String message
) {
    public OrderDetailDeleted() {
        this("GoodsType deleted successfully.");
    }
}