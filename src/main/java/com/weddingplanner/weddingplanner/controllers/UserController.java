package com.weddingplanner.weddingplanner.controllers;

import com.weddingplanner.weddingplanner.models.User;
import com.weddingplanner.weddingplanner.repositories.UserRepository;
import com.weddingplanner.weddingplanner.services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Authenticator;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User>user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet((() -> ResponseEntity.notFound().build()));
    }
    @GetMapping("/profile")
    public String profile(Authentication authentication) {
        return "User profile: " + authentication.name();
    }
}
