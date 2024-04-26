package com.autonetics.autonetics.api.service.impl;

import com.autonetics.autonetics.api.model.entity.Client;
import com.autonetics.autonetics.api.repository.ClientRepository;
import com.autonetics.autonetics.api.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        if (client != null) {
            return clientRepository.save(client);
        }
        // throw new NullEntityReferenceException("Client cannot be 'null'");
        return null;
    }

    @Override
    public Client readById(long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Client with id " + id + " not found"));
    }

    @Override
    public Client update(Client client) {
        if (client != null) {
            return clientRepository.save(client);
        }
        // throw new NullEntityReferenceException("Client cannot be 'null'");
        return null;
    }

    @Override
    public void delete(long id) {
        Client client = readById(id);
        clientRepository.delete(client);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Client client = clientRepository.findByEmail(username);
//        if (client == null) {
//            throw new UsernameNotFoundException("Client not Found!");
//        }
//        return client;
//    }
}
