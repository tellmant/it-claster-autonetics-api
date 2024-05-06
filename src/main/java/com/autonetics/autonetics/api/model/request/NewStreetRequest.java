package com.autonetics.autonetics.api.model.request;

import com.autonetics.autonetics.api.model.response.StreetTypeDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Street}
 */
public record NewStreetRequest(@NotNull @Size(max = 50) String name, @NotNull @Size(max = 50) String slangName,
                               @NotNull StreetTypeDto streetType) implements Serializable {
}