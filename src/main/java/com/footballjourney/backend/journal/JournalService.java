package com.footballjourney.backend.journal;

import com.footballjourney.backend.auth.UserRepository;
import com.footballjourney.backend.match.MatchRepository;
import com.footballjourney.backend.trip.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalEntryRepository journalEntryRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final TripRepository tripRepository;

    public JournalEntry createEntry(JournalRequest request, String userEmail) {
        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var match = matchRepository.findById(request.matchId())
                .orElseThrow(() -> new RuntimeException("Match not found"));

        var trip = (request.tripId() != null)
                ? tripRepository.findByIdAndUserEmail(request.tripId(), userEmail).orElse(null)
                : null;

        var entry = JournalEntry.builder()
                .user(user)
                .match(match)
                .trip(trip)
                .seat(request.seat())
                .ticketPrice(request.ticketPrice())
                .rating(request.rating())
                .favouriteMoment(request.favouriteMoment())
                .notes(request.notes())
                .photoUrl(request.photoUrl())
                .createdAt(LocalDateTime.now())
                .build();

        return journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getMyJournal(String userEmail) {
        return journalEntryRepository.findByUserEmailOrderByCreatedAtDesc(userEmail);
    }
}
