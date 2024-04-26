package com.autonetics.autonetics.api.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.autonetics.autonetics.api.model.entity.Client}
 */

public record NewClientRequest(@NotNull(message = "FirstName can't be null") String firstName, @NotNull String lastName,
                               LocalDate birthDate,
                               @NotNull(message = "PhoneNumber can't be null") @Pattern(message = "Phone number is wrong", regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$") String phoneNumber,
                               @NotNull(message = "Email can't be null") @Email(message = "Email isn't correct", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") String email,
                               @NotNull(message = "Password can't be null") String password,
                               String updatedBy) implements Serializable {
}