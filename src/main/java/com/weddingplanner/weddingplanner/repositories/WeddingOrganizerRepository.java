package com.weddingplanner.weddingplanner.repositories;

import com.weddingplanner.weddingplanner.models.WeddingOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeddingOrganizerRepository extends JpaRepository<WeddingOrganizer, Integer> {

}
