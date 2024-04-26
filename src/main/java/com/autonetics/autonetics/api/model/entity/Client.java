package com.autonetics.autonetics.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClientID", nullable = false)
    private Integer id;

    @Column(name = "FirstName", length = 50)
    private String firstName;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @Column(name = "Gender", length = 10)
    private String gender;

    @Column(name = "PhoneNumber", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @Column(name = "UpdatedBy", nullable = false, length = 50)
    private String updatedBy;

    @Column(name = "UpdatedOn")
    private Instant updatedOn;}

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}