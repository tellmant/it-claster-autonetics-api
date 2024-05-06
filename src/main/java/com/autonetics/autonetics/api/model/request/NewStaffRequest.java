package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Staff}
 */
public record NewStaffRequest(@NotNull @Size(max = 15) String phoneNumber, @NotNull @Size(max = 50) String email,
                              @NotNull @Size(max = 60) String password, Long staffTypeId, Long shopId,
                              @Size(max = 255) String firstName, @Size(max = 255) String lastName,
                              @Size(max = 255) String gender, LocalDate birthDate) implements Serializable {
}