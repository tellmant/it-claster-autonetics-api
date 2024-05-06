package com.autonetics.autonetics.api.model.response;

public record DeliveryGoodsUpdated(
        String message,
        long id
) {
    public DeliveryGoodsUpdated(long id) {
        this("DeliveryGoods updated successfully.", id);
    }
}
