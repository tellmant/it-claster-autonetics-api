package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_detailId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID")
    private Order orderID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GoodsID")
    private Goods goodsID;

    @Column(name = "PriceActual", precision = 10, scale = 2)
    private BigDecimal priceActual;

    @Column(name = "\"Count\"")
    private Integer count;

    @Column(name = "Discount", precision = 10, scale = 2)
    private BigDecimal discount;

    @Column(name = "Amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "AddedDateTime")
    private Instant addedDateTime;

    @Column(name = "UpdatedOn")
    private Instant updatedOn;

    @Size(max = 255)
    @Column(name = "UpdatedBy")
    private String updatedBy;

}