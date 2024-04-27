package com.autonetics.autonetics.api.auth;

import com.autonetics.autonetics.api.mapper.ClientMapper;
import com.autonetics.autonetics.api.model.entity.Client;
import com.autonetics.autonetics.api.model.response.ClientDto;
import com.autonetics.autonetics.api.repository.ClientRepository;
import com.autonetics.autonetics.api.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ClientMapper clientMapper;

    public ClientDto register(RegisterRequest clientRequest) {
        Client client = new Client();
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setBirthDate(clientRequest.getBirthDate());
        client.setPhoneNumber(clientRequest.getPhoneNumber());
        client.setEmail(clientRequest.getEmail());
        client.setPassword(passwordEncoder.encode(clientRequest.getPassword()));
        client.setUpdatedBy(clientRequest.getEmail());
        client.setUpdatedOn(Instant.now());

        clientRepository.save(client);

        return clientMapper.toDto(client);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Client client = clientRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(client);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
