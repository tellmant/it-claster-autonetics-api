package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.ShopType}
 */
public record NewShopTypeRequest(@NotNull(message = "Shop name can't be null") @Size(max = 50) String shopName,
                                 @NotNull(message = "Shop class can't be null") @Size(max = 50) String shopClass,
                                 @NotNull @Size(max = 50) String updatedBy) implements Serializable {
}