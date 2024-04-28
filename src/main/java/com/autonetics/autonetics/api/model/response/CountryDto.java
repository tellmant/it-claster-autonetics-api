package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Country}
 */
public record CountryDto(@NotNull Integer id,
                         @NotNull(message = "Country name can't be null") @Size(max = 50) String name,
                         @NotNull(message = "Country code can't be null") @Size(message = "Country code can't be more than 3 characters long", max = 3) @NotEmpty String code) implements Serializable {
}