package com.autonetics.autonetics.api.repository;

import com.autonetics.autonetics.api.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
}