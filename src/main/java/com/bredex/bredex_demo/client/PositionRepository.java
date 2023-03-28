package com.bredex.bredex_demo.client;

import com.bredex.bredex_demo.client.model.PositionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<PositionModel, Long> {
    List<PositionModel> findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(String title, String location);
}

