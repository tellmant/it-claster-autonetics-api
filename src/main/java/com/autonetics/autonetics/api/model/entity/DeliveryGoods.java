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
@Table(name = "Delivery_goods")
public class DeliveryGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Delivery_goodsID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SupplierID", nullable = false)
    private Supplier supplierID;

    @NotNull
    @Column(name = "Number", nullable = false)
    private Integer number;

    @NotNull
    @Column(name = "BeginDate", nullable = false)
    private Instant beginDate;

    @NotNull
    @Column(name = "EndDate", nullable = false)
    private Instant endDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GoodsID", nullable = false)
    private Goods goodsID;

    @Size(max = 50)
    @Column(name = "Barcode", length = 50)
    private String barcode;

    @NotNull
    @Column(name = "UpdatedOn", nullable = false)
    private Instant updatedOn;

    @Size(max = 255)
    @Column(name = "UpdatedBy")
    private String updatedBy;

}