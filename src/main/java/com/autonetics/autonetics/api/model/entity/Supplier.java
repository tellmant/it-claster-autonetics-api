package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplierID", nullable = false)
    private Integer id;

    @Size(max = 15)
    @NotNull
    @Column(name = "PhoneNumber", nullable = false, length = 15)
    private String phoneNumber;

    @NotNull
    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Size(max = 50)
    @NotNull
    @Column(name = "BankAccount", nullable = false, length = 50)
    private String bankAccount;

    @NotNull
    @Column(name = "UpdatedOn", nullable = false)
    private Instant updatedOn;

    @Size(max = 255)
    @Column(name = "UpdatedBy")
    private String updatedBy;

    @Size(max = 255)
    @Column(name = "Name")
    private String name;

}