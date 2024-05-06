package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GoodsID", nullable = false)
    private Integer id;

    @Column(name = "PriceIn", precision = 38, scale = 2)
    private BigDecimal priceIn;

    @Column(name = "PriceOut", precision = 38, scale = 2)
    private BigDecimal priceOut;

    @Column(name = "PriceAI", precision = 38, scale = 2)
    private BigDecimal priceAI;

    @Column(name = "Weight")
    private Float weight;

    @NotNull
    @Column(name = "Rating", nullable = false)
    private Integer rating;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Goods_typeID", nullable = false)
    private GoodsType goodsTypeId;

    @Size(max = 50)
    @Column(name = "Barcode", length = 50)
    private String barcode;

    @Column(name = "UpdatedOn")
    private Instant updatedOn;

    @Size(max = 255)
    @Column(name = "Name")
    private String name;

    @Size(max = 255)
    @Column(name = "Description")
    private String description;

    @Size(max = 255)
    @Column(name = "Producer")
    private String producer;

    @Size(max = 255)
    @Column(name = "StorageCondition")
    private String storageCondition;

    @Size(max = 255)
    @Column(name = "Composition")
    private String composition;

    @Size(max = 255)
    @Column(name = "UpdatedBy")
    private String updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryID")
    private Country countryID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClassID")
    private Class classID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SupplierID")
    private Supplier supplierID;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "Delivery_goods_detail",
            joinColumns = @JoinColumn(name = "GoodsID"),
            inverseJoinColumns = @JoinColumn(name = "Delivery_goodsID")
    )
    private List<DeliveryGoods> deliveredGoodsDetails;

}