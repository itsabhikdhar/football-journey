package com.footballjourney.backend.trip;

import com.footballjourney.backend.auth.UserRepository;
import com.footballjourney.backend.expense.Expense;
import com.footballjourney.backend.expense.ExpenseRepository;
import com.footballjourney.backend.expense.ExpenseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public TripSummaryDto getTripSummary(Long tripId, String userEmail) {
        Trip trip = tripRepository.findByIdAndUserEmail(tripId, userEmail)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        List<Expense> expenses = expenseRepository.findByTripId(tripId);

        BigDecimal totalSpent = expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal remaining = trip.getBudget().subtract(totalSpent);

        BigDecimal percentageUsed = BigDecimal.ZERO;
        if (trip.getBudget().compareTo(BigDecimal.ZERO) > 0) {
            percentageUsed = totalSpent
                    .divide(trip.getBudget(), 4, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"))
                    .setScale(2, RoundingMode.HALF_UP);
        }

        return  new TripSummaryDto(
                trip.getId(),
                trip.getName(),
                trip.getBudget(),
                totalSpent,
                remaining,
                percentageUsed
        );
    }

    public Trip createTrip(TripRequest request, String userEmail) {
        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var trip = Trip.builder()
                .name(request.name())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .budget(request.budget())
                .user(user)
                .build();

        return tripRepository.save(trip);
    }

    public Expense addExpense(Long tripId, ExpenseRequest request, String userEmail) {
        var trip = tripRepository.findByIdAndUserEmail(tripId, userEmail)
                .orElseThrow(() -> new RuntimeException("Trip not found or access denied"));

        var expense = Expense.builder()
                .trip(trip)
                .category(request.category())
                .description(request.description())
                .amount(request.amount())
                .expenseDate(request.expenseDate())
                .build();

        return expenseRepository.save(expense);
    }

    public List<Trip> getAllTrips(String userEmail) {
        return tripRepository.findByUserEmail(userEmail);
    }
}
