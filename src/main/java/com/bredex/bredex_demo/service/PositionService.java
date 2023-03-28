package com.bredex.bredex_demo.service;

import com.bredex.bredex_demo.client.PositionRepository;
import com.bredex.bredex_demo.client.model.PositionModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PositionService {
    private PositionRepository positionRepository;

    public List<PositionModel> searchPositions(String keyword, String location) {
        return positionRepository.findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(keyword, location);
    }

    public PositionModel addPosition(PositionModel positionModel) {
        return positionRepository.saveAndFlush(positionModel);
    }

    public PositionModel getPositionById(Long id) {
        return positionRepository.getById(id);
    }

    public boolean isValidApiKey(final UUID apiKey) {
        return true;
    }

    public boolean isValidString(final String queryParam) {
        return queryParam.length() <= 50;
    }
}
