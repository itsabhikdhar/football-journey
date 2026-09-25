package com.footballjourney.backend.stadium;

public record StadiumDto(
        Long id,
        String name,
        String city,
        String country,
        Integer capacity
) {
}
