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
@Table(name = "Shop_type")
public class ShopType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShopTypeID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "ShopName", nullable = false, length = 50)
    private String shopName;

    @Size(max = 50)
    @NotNull
    @Column(name = "ShopClass", nullable = false, length = 50)
    private String shopClass;

    @Size(max = 50)
    @NotNull
    @Column(name = "UpdatedBy", nullable = false, length = 50)
    private String updatedBy;

    @NotNull
    @Column(name = "UpdatedOn", nullable = false)
    private LocalDateTime updatedOn;

}