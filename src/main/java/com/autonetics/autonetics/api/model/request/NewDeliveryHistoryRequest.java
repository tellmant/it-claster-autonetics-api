package com.autonetics.autonetics.api.model.request;

import java.io.Serializable;

public record NewDeliveryHistoryRequest(
        Long goodsID,
        Long shopID,
        Long deliveryGoodsid
) implements Serializable {
}
