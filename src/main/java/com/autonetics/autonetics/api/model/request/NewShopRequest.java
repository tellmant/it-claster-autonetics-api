package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Shop}
 */
public record NewShopRequest(Integer clientId,  Long addressId, Long customerId, @NotNull Boolean isParking,
                             @NotNull Integer atFloor, @NotNull LocalDateTime updatedOn,
                             @Size(max = 255) String updatedBy, @Size(max = 255) String name,
                             Long shopTypeId) implements Serializable {
}