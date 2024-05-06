package com.autonetics.autonetics.api.model.response;

public record GoodsCreated(
        String message,
        long id
) {
    public GoodsCreated(long id) {
        this("GoodsType added successfully.", id);
    }
}
