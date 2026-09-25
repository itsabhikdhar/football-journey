package com.footballjourney.backend.match;

import com.footballjourney.backend.club.Club;
import com.footballjourney.backend.stadium.Stadium;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_club_id", nullable = false)
    private Club homeClub;

    @ManyToOne
    @JoinColumn(name = "away_club_id", nullable = false)
    private Club awayClub;

    @ManyToOne
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;
}
