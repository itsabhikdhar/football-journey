package com.footballjourney.backend.club;

public record ClubDto(
        Long id,
        String name,
        String country,
        String shortName
) {}
