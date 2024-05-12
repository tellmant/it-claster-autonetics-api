package com.autonetics.autonetics.api.controller;

import com.autonetics.autonetics.api.mapper.ClientMapper;
import com.autonetics.autonetics.api.model.entity.Client;
import com.autonetics.autonetics.api.model.request.NewClientRequest;
import com.autonetics.autonetics.api.model.response.ClientDto;
import com.autonetics.autonetics.api.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable long id) {
        Client client = clientService.readById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientMapper.toDto(client));
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<ClientDto> getByEmail(@PathVariable String email) {
        if (email == null) {
            return ResponseEntity.notFound().build();
        }
        Client client = clientService.findByEmail(email);
        return ResponseEntity.ok(clientMapper.toDto(client));
    }


    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody NewClientRequest newClientRequest) {
        if (newClientRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Client client = clientService.create(clientMapper.toEntity(newClientRequest));
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        client.setUpdatedOn(Instant.now());
        return new ResponseEntity<>(clientMapper.toDto(client), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable long id, @RequestBody NewClientRequest newClientRequest) {
        Client client = clientService.readById(id);
        clientMapper.partialUpdate(newClientRequest, client);

        if (newClientRequest.password() != null) {
            client.setPassword(passwordEncoder.encode(newClientRequest.password()));
        }

        client.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        client.setUpdatedOn(Instant.now());
        return ResponseEntity.ok(clientMapper.toDto(clientService.update(client)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
