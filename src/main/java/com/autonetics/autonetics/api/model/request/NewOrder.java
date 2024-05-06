package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;

public record NewOrder(
        @NotNull Instant orderDateTime,
        @NotNull Boolean isSaved,
        @NotNull Boolean isPayed,
        @NotNull BigDecimal orderAmount,
        @NotNull Integer clientID,
        @Size(max = 50) String barcode,
        @Size(max = 255) String name
) {
}
