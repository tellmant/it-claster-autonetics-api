package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Supplier}
 */
public record SupplierDto(
        Integer id,
        @NotNull @Size(max = 15) String phoneNumber,
        @NotNull String email,
        @NotNull @Size(max = 50) String bankAccount,
        @NotNull Instant updatedOn,
        @Size(max = 255) String updatedBy,
        @Size(max = 255) String name
) implements Serializable {
}