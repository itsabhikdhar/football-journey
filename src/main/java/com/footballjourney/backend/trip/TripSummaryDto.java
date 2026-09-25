package com.footballjourney.backend.trip;

import java.math.BigDecimal;

public record TripSummaryDto(
        Long tripId,
        String tripName,
        BigDecimal budget,
        BigDecimal totalSpent,
        BigDecimal remaining,
        BigDecimal budgetUsedPercentage
) {
}
