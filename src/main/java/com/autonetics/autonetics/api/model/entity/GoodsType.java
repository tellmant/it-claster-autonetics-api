package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Goods_type")
public class GoodsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Goods_typeID", nullable = false)
    private Integer id;

    @Column(name = "Name", length = 50)
    private String name;

    @Column(name = "UpdatedBy", nullable = false, length = 50)
    private String updatedBy;

    @Column(name = "UpdatedOn", nullable = false)
    private Instant updatedOn;

}