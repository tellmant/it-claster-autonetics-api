package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class DeliveryHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DeliveryHistoryId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GoodsID")
    private Goods goodsID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ShopID")
    private Shop shopID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Delivery_goodsID")
    private DeliveryGoods deliveryGoodsid;

    @Column(name = "UpdatedOn")
    private Instant updatedOn;

    @Size(max = 255)
    @Column(name = "UpdatedBy")
    private String updatedBy;

}