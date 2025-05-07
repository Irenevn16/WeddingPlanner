package com.weddingplanner.weddingplanner.repositories;

import com.weddingplanner.weddingplanner.models.WeddingOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeddingOrganizerRepository extends JpaRepository<WeddingOrganizer, Integer> {
    public WeddingOrganizer addWeddingOrganizer();
}
