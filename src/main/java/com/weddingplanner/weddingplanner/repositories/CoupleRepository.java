package com.weddingplanner.weddingplanner.repositories;

import com.weddingplanner.weddingplanner.models.Couple;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoupleRepository extends JpaRepository<Couple, Integer> {
    public Couple addCouple();
    public Couple deleteCouple();
}
