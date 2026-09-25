package com.footballjourney.backend.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping("/{id}/summary")
    public ResponseEntity<TripSummaryDto> getTripSummary(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(tripService.getTripSummary(id, principal.getName()));
    }
}
