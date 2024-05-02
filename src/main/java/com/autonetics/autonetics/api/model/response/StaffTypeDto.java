package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.StaffType}
 */

public record StaffTypeDto(@NotNull Long id, @NotNull @Size(max = 50) String name, @NotNull Boolean sellGoodsAccess,
                           @NotNull Boolean storageManagementAccess, @NotNull Boolean supplyAccess,
                           @NotNull Boolean editStoreInfoAccess, @NotNull Boolean editRoleAccess,
                           @NotNull Boolean statisticsAccess, @NotNull Boolean checkPaymentAccess,
                           @NotNull Boolean discountAccess, @NotNull Boolean priceManagementAccess,
                           @NotNull Boolean helperAccess, @NotNull @Size(max = 50) String updatedBy,
                           @NotNull LocalDateTime updatedOn) implements Serializable {
}