package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.OrderDetail}
 */
public record OrderDetailDto(
        Integer id,
        OrderDto orderID,
        GoodsDto goodsID,
        BigDecimal priceActual,
        Integer count,
        BigDecimal discount,
        BigDecimal amount,
        Instant addedDateTime,
        Instant updatedOn,
        @Size(max = 255) String updatedBy
) implements Serializable {
}