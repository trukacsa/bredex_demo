package com.bredex.bredex_demo.web;

import com.bredex.bredex_demo.client.model.PositionModel;
import com.bredex.bredex_demo.service.PositionService;
import com.bredex.bredex_demo.web.exception.JobsValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class JobController {
    @Value("${job.portal.base.url}")
    private String positionUrl;
    private PositionService positionService;

    public JobController(final PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping("/position")
    public ResponseEntity<String> addPosition(@RequestParam final UUID apiKey, @RequestBody final PositionModel positionModel) {
        if (!positionService.isValidApiKey(apiKey))
            throw new JobsValidationException("API key is invalid.");
        if (!positionService.isValidString(positionModel.getLocation(), positionModel.getTitle()))
            throw new JobsValidationException("Location and job title must be under 50 characters.");

        var addedPosition = positionService.addPosition(positionModel);
        var extendedUrl = positionUrl + addedPosition.getLocation() + "/" + addedPosition.getTitle();

        return ResponseEntity.created(URI.create(extendedUrl)).body(positionUrl);
    }

    @GetMapping("/position/search")
    public ResponseEntity<List<String>> searchPositions(@RequestParam final UUID apiKey, @RequestParam final String keyword,
                                                        @RequestParam final String location) {
        if (!positionService.isValidApiKey(apiKey))
            throw new JobsValidationException("API key is invalid.");
        if (!positionService.isValidString(keyword, location))
            throw new JobsValidationException("Keyword and location must be under 50 characters.");
        List<PositionModel> positions = positionService.searchPositions(keyword, location);
        // TODO: return URLs
        return null;
    }

    @GetMapping("/position/{id}")
    public ResponseEntity<PositionModel> getPositionById(@PathVariable final Long id) {
        // TODO: implement
        return null;
    }
}
