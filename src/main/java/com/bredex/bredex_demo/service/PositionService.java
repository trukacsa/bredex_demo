package com.bredex.bredex_demo.service;

import com.bredex.bredex_demo.client.PositionRepository;
import com.bredex.bredex_demo.client.model.PositionEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class PositionService {
    private PositionRepository positionRepository;

    public List<PositionEntity> searchPositions(final String keyword, final String location) {
        return positionRepository.findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(keyword, location);
    }

    public PositionEntity addPosition(final PositionEntity positionEntity) {
        return positionRepository.saveAndFlush(positionEntity);
    }

    public PositionEntity getPositionById(final Long id) {
        return positionRepository.getById(id);
    }

    public boolean isValidApiKey(final UUID apiKey) {
        return true;
    }

    public boolean isValidString(final String queryParam) {
        return queryParam.length() <= 50;
    }
}
