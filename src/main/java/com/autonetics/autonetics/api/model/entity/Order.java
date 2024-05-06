package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "\"Order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "OrderDateTime", nullable = false)
    private Instant orderDateTime;

    @NotNull
    @Column(name = "IsSaved", nullable = false)
    private Boolean isSaved = false;

    @NotNull
    @Column(name = "IsPayed", nullable = false)
    private Boolean isPayed = false;

    @NotNull
    @Column(name = "OrderAmount", nullable = false, precision = 10, scale = 2)
    private BigDecimal orderAmount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ClientID", nullable = false)
    private Client clientID;

    @Size(max = 50)
    @Column(name = "Barcode", length = 50)
    private String barcode;

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