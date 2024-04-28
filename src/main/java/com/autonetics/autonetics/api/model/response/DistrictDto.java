package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.District}
 */
public record DistrictDto(@NotNull(message = "District id can't be null") Integer id,
                          @NotNull(message = "District name can't be null") @Size(max = 50) String name,
                          @NotNull RegionDto region,
                          @NotNull(message = "District updated by can't be null") @Size(max = 50) String updatedBy,
                          @NotNull(message = "District updated on can't be null") LocalDateTime updatedOn) implements Serializable {
}