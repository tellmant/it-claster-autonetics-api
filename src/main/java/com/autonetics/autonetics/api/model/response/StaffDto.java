package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Staff}
 */
public record StaffDto(Long id, @NotNull @Size(max = 15) String phoneNumber, @NotNull @Size(max = 50) String email,
                       @NotNull StaffTypeDto staffType, Long shopId, LocalDateTime updatedOn,
                       @Size(max = 255) String firstName, @Size(max = 255) String lastName,
                       @Size(max = 255) String updatedBy, @Size(max = 255) String gender,
                       LocalDate birthDate) implements Serializable {
}