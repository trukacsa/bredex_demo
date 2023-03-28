package com.bredex.bredex_demo.service;

import com.bredex.bredex_demo.client.ClientRepository;
import com.bredex.bredex_demo.client.model.ClientEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ClientService {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})$");
    private ClientRepository clientRepository;

    public void addClient(final ClientEntity clientEntity) {
        clientRepository.saveAndFlush(clientEntity);
    }

    public UUID generateApiKey() {
        return UUID.randomUUID();
    }

    public boolean isValidEmail(final String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (email == null || matcher.matches()) {
            return false;
        }
        return true;
    }
}
