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
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "UpdatedOn", nullable = false)
    private Instant updatedOn;

    @Size(max = 255)
    @Column(name = "UpdatedBy")
    private String updatedBy;

    @Size(max = 255)
    @Column(name = "Name")
    private String name;

}