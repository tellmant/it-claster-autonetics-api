package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StaffID", nullable = false)
    private Long id;

    @Size(max = 15)
    @NotNull
    @Column(name = "PhoneNumber", nullable = false, length = 15)
    private String phoneNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Size(max = 60)
    @NotNull
    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Staff_typeID", nullable = false)
    private StaffType staffType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ShopID", nullable = false)
    private Shop shop;

    @Column(name = "UpdatedOn")
    private LocalDateTime updatedOn;

    @Size(max = 255)
    @Column(name = "FirstName")
    private String firstName;

    @Size(max = 255)
    @Column(name = "LastName")
    private String lastName;

    @Size(max = 255)
    @Column(name = "UpdatedBy")
    private String updatedBy;

    @Size(max = 255)
    @Column(name = "Gender")
    private String gender;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

}