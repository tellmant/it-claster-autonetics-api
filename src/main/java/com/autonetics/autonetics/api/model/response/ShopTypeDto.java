package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ShopTypeDto(@NotNull(message = "Shop name can't be null") @Size(max = 50) String shopName,
                          @NotNull(message = "Shop class can't be null") @Size(max = 50) String shopClass,
                          @NotNull(message = "Updated by can't be null") @Size(max = 50) String updatedBy,
                          @NotNull LocalDateTime updatedOn) implements Serializable {
}