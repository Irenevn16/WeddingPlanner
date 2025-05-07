package com.weddingplanner.weddingplanner.repositories;

import com.weddingplanner.weddingplanner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User addUser();
    public User updateUserInfo();
    public User updateUserPassword();
    public User updateUserUserName();

}
