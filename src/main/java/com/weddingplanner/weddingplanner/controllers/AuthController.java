package com.weddingplanner.weddingplanner.controllers;
/*
import com.weddingplanner.weddingplanner.models.*;
import com.weddingplanner.weddingplanner.repositories.CoupleRepository;
import com.weddingplanner.weddingplanner.repositories.GuestRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingOrganizerRepository;
import com.weddingplanner.weddingplanner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private WeddingOrganizerRepository weddingOrganizerRepository;

    @Autowired
    private CoupleRepository coupleRepository;

    @Autowired
    private GuestRepository guestRepository;

    @PostMapping("/register/admin")
    public WeddingOrganizer registerAdmin(@RequestBody WeddingOrganizer weddingOrganizer) {
        weddingOrganizer.setRole(Role.ROLE_ADMIN);
        return weddingOrganizerRepository.save(weddingOrganizer);
    }

    @PostMapping("/register/user")
    public Couple registerUser(@RequestBody Couple couple) {
        couple.setRole(Role.ROLE_USER);
        return coupleRepository.save(couple);
    }

    @PostMapping("/register/guest")
    public Guest registerGuest(@RequestBody Guest guest) {
        guest.setRole(Role.ROLE_GUEST);
        return guestRepository.save(guest);
    }
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> optionalUser = UserService.findByUsername(user.getUsername());

        if (optionalUser.isPresent()) {
            //extraemos obj dentro de optional
            User foundUser = optionalUser.get();
            //comprobamos si la contra es correcta
            if (UserService.checkPassword(foundUser, user.getPassword())) {
                List<String> roleNames = foundUser.getRoles().stream()
                        .map(role -> role.getName().name())
                        .collect(Collectors.toList());

                String token = jwtService.generateToken(foundUser.getUsername(), foundUser.getRoles().toString());
                AuthResponseDto response = new AuthResponseDto();
                response.setToken(token);
                response.setUsername(foundUser.getUsername());
                response.setRoles(roleNames);

                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect login");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}
*/