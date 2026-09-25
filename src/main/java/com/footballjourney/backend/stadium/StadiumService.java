package com.footballjourney.backend.stadium;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    public List<StadiumDto> getAllStadiums() {
        return stadiumRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public StadiumDto getStadiumById(Long id) {
        Stadium stadium = stadiumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stadium not found"));
        return mapToDto(stadium);
    }

    private StadiumDto mapToDto(Stadium stadium) {
        return new StadiumDto(
                stadium.getId(),
                stadium.getName(),
                stadium.getCity(),
                stadium.getCountry(),
                stadium.getCapacity()
        );
    }

}
