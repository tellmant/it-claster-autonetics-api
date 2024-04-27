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
@Table(name = "Goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GoodsID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "PriceIn", precision = 38, scale = 2)
    private BigDecimal priceIn;

    @Column(name = "PriceOut", precision = 38, scale = 2)
    private BigDecimal priceOut;

    @Column(name = "PriceAI", precision = 38, scale = 2)
    private BigDecimal priceAI;

    @Column(name = "Weight")
    private Float weight;

    @Size(max = 300)
    @NotNull
    @Column(name = "Description", nullable = false, length = 300)
    private String description;

    @Size(max = 50)
    @NotNull
    @Column(name = "Producer", nullable = false, length = 50)
    private String producer;

    @Column(name = "CountryID")
    private Long countryID;

    @Size(max = 100)
    @NotNull
    @Column(name = "StorageCondition", nullable = false, length = 100)
    private String storageCondition;

    @Size(max = 1000)
    @NotNull
    @Column(name = "Composition", nullable = false, length = 1000)
    private String composition;

    @Column(name = "SupplierID")
    private Long supplierID;

    @NotNull
    @Column(name = "Rating", nullable = false)
    private Integer rating;

    @Column(name = "ClassID")
    private Long classID;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Goods_typeID", nullable = false)
    private GoodsType goodsTypeId;

    @Size(max = 50)
    @Column(name = "Barcode", length = 50)
    private String barcode;

    @Size(max = 50)
    @NotNull
    @Column(name = "UpdatedBy", nullable = false, length = 50)
    private String updatedBy;

    @Column(name = "UpdatedOn")
    private Instant updatedOn;

}