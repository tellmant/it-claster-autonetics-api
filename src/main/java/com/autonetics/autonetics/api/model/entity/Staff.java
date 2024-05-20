package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Staff implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StaffID", nullable = false)
    private Long id;

    @Size(max = 15)
    @NotNull
    @Column(name = "PhoneNumber", nullable = false, length = 15)
    private String phoneNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Size(max = 60)
    @NotNull
    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Staff_typeID", nullable = false)
    private StaffType staffType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ShopID", nullable = false)
    private Shop shop;

    @Column(name = "UpdatedOn")
    private Instant updatedOn;

    @Size(max = 255)
    @Column(name = "FirstName")
    private String firstName;

    @Size(max = 255)
    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Role", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Size(max = 255)
    @Column(name = "UpdatedBy")
    private String updatedBy;

    @Size(max = 255)
    @Column(name = "Gender")
    private String gender;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff staff)) return false;
        return Objects.equals(id, staff.id) && Objects.equals(phoneNumber, staff.phoneNumber) && Objects.equals(email, staff.email) && Objects.equals(password, staff.password) && Objects.equals(staffType, staff.staffType) && Objects.equals(shop, staff.shop) && Objects.equals(updatedOn, staff.updatedOn) && Objects.equals(firstName, staff.firstName) && Objects.equals(lastName, staff.lastName) && role == staff.role && Objects.equals(updatedBy, staff.updatedBy) && Objects.equals(gender, staff.gender) && Objects.equals(birthDate, staff.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, email, password, staffType, shop, updatedOn, firstName, lastName, role, updatedBy, gender, birthDate);
    }
}