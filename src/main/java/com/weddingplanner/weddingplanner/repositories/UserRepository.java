package com.weddingplanner.weddingplanner.repositories;

import com.weddingplanner.weddingplanner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
