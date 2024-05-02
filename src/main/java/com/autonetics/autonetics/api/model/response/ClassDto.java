package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Class}
 */
public record ClassDto(
        Integer id,
        @NotNull Instant updatedOn,
        @Size(max = 255) String updatedBy,
        @Size(max = 255) String name
) implements Serializable {
}