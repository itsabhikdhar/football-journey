package com.footballjourney.backend.journal;

import com.footballjourney.backend.auth.User;
import com.footballjourney.backend.match.Match;
import com.footballjourney.backend.trip.Trip;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "journal_entries")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    private String seat;

    @Column(name = "ticket_price", precision = 10, scale = 2)
    private BigDecimal ticketPrice;

    private Integer rating;

    @Column(name = "favourite_moment", length = 500)
    private String favouriteMoment;

    @Column(length = 2000)
    private String notes;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
