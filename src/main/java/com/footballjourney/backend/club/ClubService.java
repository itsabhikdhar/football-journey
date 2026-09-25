package com.footballjourney.backend.club;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public List<ClubDto> getAllClubs() {
        return clubRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ClubDto getClubById(Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found"));
        return mapToDto(club);
    }

    private ClubDto mapToDto(Club club) {
        return new ClubDto(
                club.getId(),
                club.getName(),
                club.getCountry(),
                club.getShortName()
        );
    }
}
