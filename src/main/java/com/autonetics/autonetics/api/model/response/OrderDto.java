package com.autonetics.autonetics.api.model.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Order}
 */
public record OrderDto(
        Integer id,
        @NotNull Instant orderDateTime,
        @NotNull Boolean isSaved,
        @NotNull Boolean isPayed,
        @NotNull BigDecimal orderAmount,
        @NotNull ClientDto clientID,
        @Size(max = 50) String barcode,
        @NotNull Instant updatedOn,
        @Size(max = 255) String updatedBy,
        @Size(max = 255) String name
) implements Serializable {
}