package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AddressID", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SettlementID", nullable = false)
    private Settlement settlement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StreetID")
    private Street street;

    @Column(name = "Latitude", precision = 8, scale = 6)
    private BigDecimal latitude;

    @Column(name = "Longitude", precision = 9, scale = 6)
    private BigDecimal longitude;

    @Size(max = 50)
    @NotNull
    @Column(name = "UpdatedBy", nullable = false, length = 50)
    private String updatedBy;

    @NotNull
    @Column(name = "UpdatedOn", nullable = false)
    private LocalDateTime updatedOn;

}