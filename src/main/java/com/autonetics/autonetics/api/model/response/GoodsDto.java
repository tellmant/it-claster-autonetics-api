package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Goods}
 */
public record GoodsDto(
        Integer id,
        BigDecimal priceIn,
        BigDecimal priceOut,
        BigDecimal priceAI,
        String photo,
        Float weight,
        @NotNull Integer rating,
        @NotNull GoodsTypeDto goodsTypeId,
        @Size(max = 50) String barcode,
        Instant updatedOn,
        @Size(max = 255) String name,
        @Size(max = 255) String description,
        @Size(max = 255) String producer,
        @Size(max = 255) String storageCondition,
        @Size(max = 255) String composition,
        @Size(max = 255) String updatedBy,
        CountryDto countryID,
        ClassDto classID,
        SupplierDto supplierID
) implements Serializable {
}