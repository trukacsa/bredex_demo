package com.bredex.bredex_demo.service;

import com.bredex.bredex_demo.client.model.PositionModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PositionService {
    private List<PositionModel> positions = new ArrayList<>();

    public PositionService() {
        PositionModel position1 = new PositionModel("Java Developer", "Budapest");
        PositionModel position2 = new PositionModel("Backend Java Developer", "New York");
        PositionModel position3 = new PositionModel("Frontend Developer", "London");
        positions = Arrays.asList(position1, position2, position3);
    }

    public PositionModel addPosition(final PositionModel positionModel) {
        positions.add(positionModel);
        return positionModel;
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

    public boolean isValidString(final String location, final String position) {
        if (location.length() > 50) return false;
        return position.length() <= 50;
    }
}
