package com.bredex.bredex_demo.client;

import com.bredex.bredex_demo.client.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {
}
