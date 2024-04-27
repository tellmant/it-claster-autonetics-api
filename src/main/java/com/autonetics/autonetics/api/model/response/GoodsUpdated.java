package com.autonetics.autonetics.api.model.response;

public record GoodsUpdated(
        String message
) {
    public GoodsUpdated() {
        this("Goods updated successfully.");
    }
}
