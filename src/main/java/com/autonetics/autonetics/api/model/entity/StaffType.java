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
@Table(name = "Staff_type")
public class StaffType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Staff_typeID", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @NotNull
    @Column(name = "SellGoodsAccess", nullable = false)
    private Boolean sellGoodsAccess = false;

    @NotNull
    @Column(name = "StorageManagementAccess", nullable = false)
    private Boolean storageManagementAccess = false;

    @NotNull
    @Column(name = "SupplyAccess", nullable = false)
    private Boolean supplyAccess = false;

    @NotNull
    @Column(name = "EditStoreInfoAccess", nullable = false)
    private Boolean editStoreInfoAccess = false;

    @NotNull
    @Column(name = "EditRoleAccess", nullable = false)
    private Boolean editRoleAccess = false;

    @NotNull
    @Column(name = "StatisticsAccess", nullable = false)
    private Boolean statisticsAccess = false;

    @NotNull
    @Column(name = "CheckPaymentAccess", nullable = false)
    private Boolean checkPaymentAccess = false;

    @NotNull
    @Column(name = "DiscountAccess", nullable = false)
    private Boolean discountAccess = false;

    @NotNull
    @Column(name = "PriceManagementAccess", nullable = false)
    private Boolean priceManagementAccess = false;

    @NotNull
    @Column(name = "HelperAccess", nullable = false)
    private Boolean helperAccess = false;

    @Size(max = 50)
    @NotNull
    @Column(name = "UpdatedBy", nullable = false, length = 50)
    private String updatedBy;

    @NotNull
    @Column(name = "UpdatedOn", nullable = false)
    private LocalDateTime updatedOn;

}