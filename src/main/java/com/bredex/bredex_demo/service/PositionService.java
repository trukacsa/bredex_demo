package com.bredex.bredex_demo.service;

import com.bredex.bredex_demo.client.PositionRepository;
import com.bredex.bredex_demo.client.model.PositionEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;
    private ClientService clientService;

    public List<PositionEntity> searchPositions(final String keyword, final String location) {
        return positionRepository.findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(keyword, location);
    }

    public PositionEntity addPosition(final PositionEntity positionEntity) {
        return positionRepository.saveAndFlush(positionEntity);
    }

    public PositionEntity getPositionById(final Long id) {
        return positionRepository.getById(id);
    }

    public boolean isValidString(final String queryParam) {
        return queryParam.length() <= 50;
    }
}
