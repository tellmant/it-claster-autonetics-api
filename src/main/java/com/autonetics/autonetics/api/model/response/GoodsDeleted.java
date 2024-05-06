package com.autonetics.autonetics.api.model.response;

public record GoodsDeleted(
        String message,
        long id
) {
    public GoodsDeleted(long id) {
        this("Goods deleted successfully.", id);
    }
}
