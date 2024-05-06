package com.autonetics.autonetics.api.model.response;

public record DeliveryGoodsCreated(
        String message,
        long id
) {
    public DeliveryGoodsCreated(long id) {
        this("DeliveryGoods added successfully.", id);
    }
}
