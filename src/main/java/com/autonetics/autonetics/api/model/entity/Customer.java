package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AddressID", nullable = false)
    private Address address;

    @NotNull
    @Column(name = "OnlineShoping", nullable = false)
    private Boolean onlineShoping = false;

    @Size(max = 100)
    @NotNull
    @Column(name = "Website", nullable = false, length = 100)
    private String website;

    @NotNull
    @Column(name = "UpdatedOn", nullable = false)
    private LocalDateTime updatedOn;

    @Size(max = 255)
    @Column(name = "UpdatedBy")
    private String updatedBy;

    @Size(max = 255)
    @Column(name = "Name")
    private String name;

}