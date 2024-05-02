package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.District}
 */
public record NewDistrictRequest(@NotNull @Size(max = 50) String name, Integer regionId) implements Serializable {
}