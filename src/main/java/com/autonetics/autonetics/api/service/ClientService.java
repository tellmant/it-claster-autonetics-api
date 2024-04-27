package com.autonetics.autonetics.api.service;

import com.autonetics.autonetics.api.model.entity.Client;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClientService extends UserDetailsService { //must extend UserDetailsService
    Client create(Client client);
    Client readById(long id);
    Client update(Client client);
    void delete(long id);
    List<Client> getAll();
    Client findByEmail(String email);
}
