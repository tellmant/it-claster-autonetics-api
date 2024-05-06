package com.autonetics.autonetics.api.model.response;

public record GoodsTypeCreated(
        String message,
        long id
) {
    public GoodsTypeCreated(long id) {
        this("GoodsType added successfully.", id);
    }
}
