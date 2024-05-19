package com.autonetics.autonetics.api.model.response;

public record OrderDetailUpdated (
        String message,
        long id
){
    public OrderDetailUpdated(long id) {
        this("OrderDetail updated successfully.", id);
    }
}
