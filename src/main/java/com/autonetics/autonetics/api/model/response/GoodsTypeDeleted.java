package com.autonetics.autonetics.api.model.response;

public record GoodsTypeDeleted(
        String message,
        long id
) {
    public GoodsTypeDeleted(long id) {
        this("GoodsType deleted successfully.", id);
    }
}
