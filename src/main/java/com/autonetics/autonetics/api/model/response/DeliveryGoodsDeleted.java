package com.autonetics.autonetics.api.model.response;

public record DeliveryGoodsDeleted(
        String message
) {
    public DeliveryGoodsDeleted() {
        this("DeliveryGoods deleted successfully.");
    }
}
