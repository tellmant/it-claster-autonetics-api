package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.ClientMapper;
import com.autonetics.autonetics.api.model.entity.Client;
import com.autonetics.autonetics.api.model.request.NewClientRequest;
import com.autonetics.autonetics.api.model.response.ClientDto;
import com.autonetics.autonetics.api.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;


    @GetMapping
    @Transactional(readOnly = true)
    public List<ClientDto> getAll() {
        return clientService.getAll().stream().map(clientMapper::toDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable long id) {
        Client client = clientService.readById(id);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientMapper.toDto(client), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@Validated @RequestBody NewClientRequest newClientRequest) {
        if (newClientRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Client client = clientService.create(clientMapper.toEntity(newClientRequest));
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setUpdatedOn(Instant.now());
        return new ResponseEntity<>(clientMapper.toDto(client), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable long id,@Validated @RequestBody NewClientRequest newClientRequest) {
        Client client = clientService.readById(id);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        client = clientMapper.toEntity(newClientRequest);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client = clientService.update(client);
        return new ResponseEntity<>(clientMapper.toDto(client), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
