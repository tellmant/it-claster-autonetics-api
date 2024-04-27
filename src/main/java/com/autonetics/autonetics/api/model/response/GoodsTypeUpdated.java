package com.autonetics.autonetics.api.model.response;

public record GoodsTypeUpdated(
        String message
) {
    public GoodsTypeUpdated() {
        this("GoodsType updated successfully.");
    }
}
