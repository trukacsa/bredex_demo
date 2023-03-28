package com.bredex.bredex_demo.web;

import com.bredex.bredex_demo.client.model.ClientModel;
import com.bredex.bredex_demo.service.ClientService;
import com.bredex.bredex_demo.web.exception.ClientValidationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class ClientController {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})$");
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client")
    public UUID registerClient(@RequestBody final ClientModel client) {
        var email = client.getEmail();
        if (!isValidEmail(email)) {
            throw new ClientValidationException("Email: {} is invalid", email);
        }
        if (!clientService.isUniqueEmail(email)) {
            throw new ClientValidationException("Email: {} is taken.", email);
        }
        UUID apiKey = clientService.generateApiKey();
        clientService.addClient(client);
        return apiKey;
    }

    private boolean isValidEmail(final String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (email == null || matcher.matches()) {
            return false;
        }
        return true;
    }
}

