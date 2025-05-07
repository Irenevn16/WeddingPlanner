package com.weddingplanner.weddingplanner.repositories;

import com.weddingplanner.weddingplanner.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {

    public Guest addGuest();
    public Guest updateGuestCompannion();
    public Guest deleteGuest();
}
