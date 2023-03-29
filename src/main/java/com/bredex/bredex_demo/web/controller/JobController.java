package com.bredex.bredex_demo.web.controller;

import com.bredex.bredex_demo.client.model.PositionEntity;
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

    @PostMapping("/position")
    public ResponseEntity<String> addPosition(@RequestParam final UUID apiKey, @RequestBody final PositionEntity positionEntity) {
        if (!positionService.isValidApiKey(apiKey))
            throw new ValidationException("API key: {} is invalid.", apiKey.toString());
        if (!positionService.isValidString(positionEntity.getLocation()))
            throw new ValidationException("Location: {} must be under 50 characters.", positionEntity.getLocation());
        if (!positionService.isValidString(positionEntity.getTitle()))
            throw new ValidationException("Location: {} must be under 50 characters.", positionEntity.getLocation());

        var addedPosition = positionService.addPosition(positionEntity);
        var extendedUrl = baseUrl + LOCATION_PARAM + addedPosition.getLocation() + KEYWORD_PARAM + addedPosition.getTitle();

        return ResponseEntity.created(URI.create(extendedUrl)).body(extendedUrl);
    }

    @GetMapping("/position/search")
    public ResponseEntity<List<String>> searchPositions(@RequestParam final UUID apiKey, @RequestParam final String keyword, @RequestParam final String location) {
        if (!positionService.isValidApiKey(apiKey))
            throw new ValidationException("API key: {} is invalid.", apiKey.toString());
        if (!positionService.isValidString(keyword))
            throw new ValidationException("Keyword: {} must be under 50 characters.", keyword);
        if (!positionService.isValidString(location))
            throw new ValidationException("Location: {} must be under 50 characters.", location);
        List<PositionEntity> positions = positionService.searchPositions(keyword, location);

        List<String> positionUrls = positions.stream()
                .map(position -> baseUrl + LOCATION_PARAM + position.getLocation() + KEYWORD_PARAM + position.getTitle())
                .collect(Collectors.toList());
        return ResponseEntity.ok(positionUrls);
    }

    @GetMapping("/position/{id}")
    public ResponseEntity<PositionEntity> getPositionById(@PathVariable final Long id) {
        if (id == null) {
            throw new ValidationException("Position ID must be not null");
        }
        return ResponseEntity.ok(positionService.getPositionById(id));
    }
}
