package com.weddingplanner.weddingplanner;

import com.weddingplanner.weddingplanner.models.User;
import com.weddingplanner.weddingplanner.repositories.UserRepository;
import com.weddingplanner.weddingplanner.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MethodsTest {

    @Autowired
    UserService userService;


    @Test
    public void testFindByUsername() { //FUNCIONA

        Optional<User> user = userService.findByUsername("Lu3");
        assertTrue(user.isPresent());
        assertEquals("Lu3", user.get().getUsername());
    }
}
