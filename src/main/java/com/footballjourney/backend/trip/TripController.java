package com.footballjourney.backend.trip;

import com.footballjourney.backend.expense.Expense;
import com.footballjourney.backend.expense.ExpenseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping("/{id}/summary")
    public ResponseEntity<TripSummaryDto> getTripSummary(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(tripService.getTripSummary(id, principal.getName()));
    }

    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody TripRequest request, Principal principal) {
        return ResponseEntity.ok(tripService.createTrip(request, principal.getName()));
    }

    @PostMapping("/{tripId}/expenses")
    public ResponseEntity<Expense> addExpense(@PathVariable Long tripId, @RequestBody ExpenseRequest request, Principal principal) {
        return ResponseEntity.ok(tripService.addExpense(tripId, request, principal.getName()));
    }

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips(Principal principal) {
        return ResponseEntity.ok(tripService.getAllTrips(principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(tripService.getTripById(id, principal.getName()));
    }
}
