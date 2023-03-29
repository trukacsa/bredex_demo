package com.bredex.bredex_demo.client;

import com.bredex.bredex_demo.client.model.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    List<PositionEntity> findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(String title, String location);
}

