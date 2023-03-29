package com.bredex.bredex_demo.web.controller;

import com.bredex.bredex_demo.client.model.ClientEntity;
import com.bredex.bredex_demo.service.ClientService;
import com.bredex.bredex_demo.web.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/client")
    public UUID registerClient(@RequestBody final ClientEntity clientEntity) {
        var email = clientEntity.getEmail();
        if (!clientService.isValidEmail(email)) {
            throw new ValidationException("Email address is invalid");
        }
        UUID apiKey = clientService.generateApiKey();
        clientEntity.setApiKey(apiKey);
        clientService.addClient(clientEntity);
        return apiKey;
    }
}