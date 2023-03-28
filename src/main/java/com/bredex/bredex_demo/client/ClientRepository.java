package com.bredex.bredex_demo.client;

import com.bredex.bredex_demo.client.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
