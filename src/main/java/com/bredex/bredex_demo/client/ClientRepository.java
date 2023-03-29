package com.bredex.bredex_demo.client;

import com.bredex.bredex_demo.client.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    ClientEntity findByApiKey(final UUID apiKey);

    boolean existsByEmail(final String email);
}
