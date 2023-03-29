package com.bredex.bredex_demo.web.controller;

import com.bredex.bredex_demo.client.model.PositionEntity;
import com.bredex.bredex_demo.service.ClientService;
import com.bredex.bredex_demo.service.PositionService;
import com.bredex.bredex_demo.web.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JobController {
    public static final String LOCATION_PARAM = "?location=";
    public static final String KEYWORD_PARAM = "&keyword=";
    @Value("${job.portal.base.url}")
    private String baseUrl;
    private final PositionService positionService;
    private final ClientService clientService;

    @PostMapping("/position")
    public ResponseEntity<String> addPosition(@RequestParam final UUID apiKey, @RequestBody final PositionEntity positionEntity) {
        if (!clientService.isValidApiKey(apiKey))
            throw new ValidationException("API key is invalid.", apiKey.toString());
        if (!positionService.isValidString(positionEntity.getLocation()))
            throw new ValidationException("Job location must be under 50 characters.", positionEntity.getLocation());
        if (!positionService.isValidString(positionEntity.getTitle()))
            throw new ValidationException("Job title must be under 50 characters.", positionEntity.getLocation());

        var addedPosition = positionService.addPosition(positionEntity);
        var extendedUrl = baseUrl + LOCATION_PARAM + removeSpaces(addedPosition.getLocation()) + KEYWORD_PARAM + removeSpaces(addedPosition.getTitle());

        return ResponseEntity.created(URI.create(extendedUrl)).body(extendedUrl);
    }

    @GetMapping("/position/search")
    public ResponseEntity<List<String>> searchPositions(@RequestParam final UUID apiKey, @RequestParam final String keyword, @RequestParam final String location) {
        if (!clientService.isValidApiKey(apiKey))
            throw new ValidationException("API key is invalid.", apiKey.toString());
        if (!positionService.isValidString(keyword))
            throw new ValidationException("Job title keyword must be under 50 characters.", keyword);
        if (!positionService.isValidString(location))
            throw new ValidationException("Location must be under 50 characters.", location);
        List<PositionEntity> positions = positionService.searchPositions(keyword, location);
        if (positions == null || positions.isEmpty()) {
            throw new ValidationException("No positions found with the given keyword and location.");
        }
        List<String> positionUrls = positions.stream()
                .map(position -> baseUrl + LOCATION_PARAM + removeSpaces(position.getLocation()) + KEYWORD_PARAM + removeSpaces(position.getTitle()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(positionUrls);
    }

    @GetMapping("/position/{id}")
    public ResponseEntity<PositionEntity> getPositionById(@PathVariable final Long id) {
        if (id == null) {
            throw new ValidationException("Position ID must be not null");
        }
        PositionEntity position = positionService.getPositionById(id);
        if (position == null) {
            throw new ValidationException("Position with ID not found", String.valueOf(id));
        }
        return ResponseEntity.ok(position);
    }

    private String removeSpaces(final String toBeModified) {
        return toBeModified.replace(" ", "%20");
    }
}
