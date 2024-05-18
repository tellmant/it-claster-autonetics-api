package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Inventory}
 */
public record NewInventoryRequest(Integer goodsId, Long shopId, @NotNull Integer numbers) implements Serializable {
}