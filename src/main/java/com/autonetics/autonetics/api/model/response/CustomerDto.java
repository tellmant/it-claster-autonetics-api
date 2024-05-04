package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Customer}
 */
public record CustomerDto(Long id, Long addressId, @NotNull Boolean onlineShoping,
                          @NotNull @Size(max = 100) String website, @NotNull LocalDateTime updatedOn,
                          @Size(max = 255) String updatedBy, @Size(max = 255) String name) implements Serializable {
}