package com.autonetics.autonetics.api.model.request;

import com.autonetics.autonetics.api.model.response.RegionDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.District}
 */
public record NewDistrictRequest(@NotNull(message = "District name can't be null") @Size(max = 50) String name,
                                 @NotNull(message = "Region can't be null") RegionDto region,
                                 @NotNull(message = "District updated by can't be null") @Size(max = 50) String updatedBy) implements Serializable {
}