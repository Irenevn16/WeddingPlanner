package com.weddingplanner.weddingplanner;

import com.weddingplanner.weddingplanner.models.Couple;
import com.weddingplanner.weddingplanner.models.Role;
import com.weddingplanner.weddingplanner.repositories.UserRepository;
import com.weddingplanner.weddingplanner.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private Couple testCouple;

    @BeforeEach
    public void setUp(){
        testCouple = new Couple();
        testCouple.setName("Maria");
        testCouple.setUsername("MariadelaO");
        testCouple.setPassword("123");
        testCouple.setAge(36);
        testCouple.setRole(Role.ROLE_EDITOR);

        userService.saveUser(testCouple);
    }
    @AfterEach
    public void tearDown(){
        userRepository.delete(testCouple);
    }

    @Test
    @DisplayName("Password encryption works")
    public void passwordEncryption() { //FUNCIONA
        assertTrue(testCouple.getPassword().startsWith("$2a$"));
    }

    @Test
    @DisplayName("Password correct")
    public void passwordCorrect(){ //FUNCIONA
        boolean isCorrect = userService.checkPassword(testCouple, "123");
        assertTrue(isCorrect);
        System.out.println("Is the password correct? " + isCorrect);
    }
}
