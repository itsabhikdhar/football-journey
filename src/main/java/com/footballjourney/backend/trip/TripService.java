package com.footballjourney.backend.trip;

import com.footballjourney.backend.expense.Expense;
import com.footballjourney.backend.expense.ExpenseRepository;
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

}
