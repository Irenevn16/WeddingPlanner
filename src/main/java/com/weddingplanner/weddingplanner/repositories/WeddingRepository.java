package com.weddingplanner.weddingplanner.repositories;

import com.weddingplanner.weddingplanner.models.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeddingRepository extends JpaRepository<Wedding, Integer> {


}
