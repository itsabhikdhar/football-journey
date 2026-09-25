package com.footballjourney.backend.trip;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TripRequest(
        String name,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal budget
) {
}
