package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Inventory}
 */
public record InventoryDto(Long id, GoodsDto goodsID, ShopDto shopID, Integer numbers, LocalDateTime updatedOn,
                           @Size(max = 255) String updatedBy) implements Serializable {
}