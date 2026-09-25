package com.footballjourney.backend.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(
        String category,
        String description,
        BigDecimal amount,
        LocalDate expenseDate
) {
}
