package com.bredex.bredex_demo.client;

import com.bredex.bredex_demo.client.model.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    List<PositionEntity> findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(String title, String location);
}

