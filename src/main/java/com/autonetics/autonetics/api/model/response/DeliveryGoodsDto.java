package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.DeliveryGoods}
 */
public record DeliveryGoodsDto(
        Integer id,
        @NotNull SupplierDto supplierID,
        @NotNull Integer number,
        @NotNull Instant beginDate,
        @NotNull Instant endDate,
        @NotNull GoodsDto goodsID,
        @Size(max = 50) String barcode,
        @NotNull Instant updatedOn,
        @Size(max = 255) String updatedBy
) implements Serializable {
}