package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;

public record NewOrderDetail(
        Integer id,
        Long orderID,
        Long goodsID,
        BigDecimal priceActual,
        Integer count,
        BigDecimal discount,
        BigDecimal amount,
        Instant addedDateTime,
        Instant updatedOn,
        @Size(max = 255) String updatedBy
) {
}
