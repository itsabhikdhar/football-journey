package com.footballjourney.backend.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByUserEmail(String email);
    Optional<Trip> findByIdAndUserEmail(Long id, String email);
}
