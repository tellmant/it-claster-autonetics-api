package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.StaffType}
 */
@Value
public class NewStaffTypeRequest implements Serializable {
    @NotNull
    @Size(max = 50)
    String name;
    @NotNull
    Boolean sellGoodsAccess;
    @NotNull
    Boolean storageManagementAccess;
    @NotNull
    Boolean supplyAccess;
    @NotNull
    Boolean editStoreInfoAccess;
    @NotNull
    Boolean editRoleAccess;
    @NotNull
    Boolean statisticsAccess;
    @NotNull
    Boolean checkPaymentAccess;
    @NotNull
    Boolean discountAccess;
    @NotNull
    Boolean priceManagementAccess;
    @NotNull
    Boolean helperAccess;
    @NotNull
    @Size(max = 50)
    String updatedBy;
}