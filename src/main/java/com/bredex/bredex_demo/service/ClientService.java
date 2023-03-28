package com.bredex.bredex_demo.service;

import com.bredex.bredex_demo.client.model.ClientModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    private List<ClientModel> clients = new ArrayList<>();

    public void addClient(final ClientModel clientModel) {
        clients.add(clientModel);
    }

    public UUID generateApiKey() {
        return UUID.randomUUID();
    }

    public boolean isUniqueEmail(final String email) {
        return true;
    }
}
