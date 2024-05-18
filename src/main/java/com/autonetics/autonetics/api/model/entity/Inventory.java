package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoriesId", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GoodsID")
    private Goods goodsID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ShopID")
    private Shop shopID;

    @Column(name = "Numbers")
    private Integer numbers;

    @Column(name = "UpdatedOn")
    private LocalDateTime updatedOn;

    @Size(max = 255)
    @Column(name = "UpdatedBy")
    private String updatedBy;

}