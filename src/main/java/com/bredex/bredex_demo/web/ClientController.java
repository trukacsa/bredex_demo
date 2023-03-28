package com.bredex.bredex_demo.web;

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
    public UUID registerClient(@RequestBody final ClientEntity client) {
        var email = client.getEmail();
        if (!clientService.isValidEmail(email)) {
            throw new ValidationException("Email address is invalid");
        }
        if (!clientService.isValidEmail(email)) {
            throw new ValidationException("Email address is taken.");
        }
        UUID apiKey = clientService.generateApiKey();
        clientService.addClient(client);
        return apiKey;
    }

}