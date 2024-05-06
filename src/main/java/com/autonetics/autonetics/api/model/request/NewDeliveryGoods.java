package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.Instant;

@Builder
public record NewDeliveryGoods(
        @NotNull long supplierID,
        @NotNull long number,
        @NotNull Instant beginDate,
        @NotNull Instant endDate,
        @NotNull long goodsID,
        @Size(max = 50) String barcode
) {
}
