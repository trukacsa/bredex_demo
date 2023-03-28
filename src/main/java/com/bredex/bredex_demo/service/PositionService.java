package com.bredex.bredex_demo.service;

import com.bredex.bredex_demo.client.model.PositionModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PositionService {
    private List<PositionModel> positions = new ArrayList<>();

    public void addPosition(final PositionModel positionModel) {
        positions.add(positionModel);
    }

    public List<PositionModel> searchPositions(final String keyword, final String location) {
        return positions.stream()
                .filter(position -> position.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .filter(position -> position.getLocation().toLowerCase().contains(location.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean isValidApiKey(final UUID apiKey) {
        return true;
    }
}

