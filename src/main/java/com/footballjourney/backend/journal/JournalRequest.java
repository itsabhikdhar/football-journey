package com.footballjourney.backend.journal;

import java.math.BigDecimal;

public record JournalRequest(
        Long matchId,
        Long tripId,
        String seat,
        BigDecimal ticketPrice,
        Integer rating,
        String favouriteMoment,
        String notes,
        String photoUrl
) {
}
