package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.DeliveryHistory}
 */
public record DeliveryHistoryDto(
        Integer id,
        GoodsDto goodsID,
        ShopDto shopID,
        DeliveryGoodsDto deliveryGoodsid,
        Instant updatedOn,
        @Size(max = 255) String updatedBy
) implements Serializable {
}