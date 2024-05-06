package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Region}
 */
public record NewRegionRequest(@NotNull @Size(max = 50) String name, Integer countryId) implements Serializable {
}