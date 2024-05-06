package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Street_type")
public class StreetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Street_typeID", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "Name", length = 50)
    private String name;

}