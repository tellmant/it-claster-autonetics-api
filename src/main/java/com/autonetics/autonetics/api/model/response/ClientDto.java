package com.autonetics.autonetics.api.model.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

public record ClientDto(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthDate, String gender,
        @NotNull(message = "phone number can't be null") String phoneNumber,
        @NotNull(message = "email can't be null") @Email(message = "must be a valid email", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") String email,
        @NotNull(message = "updatedBy can't be null") String updatedBy,
        Instant updatedOn) implements Serializable {
}