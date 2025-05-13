package com.weddingplanner.weddingplanner.controllers;

import com.weddingplanner.weddingplanner.dto.AuthResponseDto;
import com.weddingplanner.weddingplanner.models.*;
import com.weddingplanner.weddingplanner.repositories.CoupleRepository;
import com.weddingplanner.weddingplanner.repositories.GuestRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingOrganizerRepository;
import com.weddingplanner.weddingplanner.services.JwtService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register/admin")
    public WeddingOrganizer registerAdmin(@RequestBody WeddingOrganizer weddingOrganizer) {
        weddingOrganizer.setRole(Role.ROLE_ADMIN);
        return weddingOrganizerRepository.save(weddingOrganizer);
    }

    @PostMapping("/register/editor")
    public Couple registerEditor(@RequestBody Couple couple) {
        couple.setRole(Role.ROLE_EDITOR);
        return coupleRepository.save(couple);
    }

    @PostMapping("/register/guest")
    public Guest registerGuest(@RequestBody Guest guest) {
        guest.setRole(Role.ROLE_GUEST);
        return guestRepository.save(guest);
    }

    public ResponseEntity<AuthResponseDto> login(@RequestBody User loginRequest) {
        Optional<User> optionalUser = userService.findByUsername(loginRequest.getUsername());

        if (optionalUser.isPresent()) {
            //extraemos obj dentro de optional
            User foundUser = optionalUser.get();
            //comprobamos si la contra es correcta
            if (userService.checkPassword(foundUser, loginRequest.getPassword())) {
                String role;
                if (foundUser instanceof Guest) {
                    role = "ROLE_GUEST";
                } else if (foundUser instanceof Couple) {
                    role = "ROLE_EDITOR";
                } else if (foundUser instanceof WeddingOrganizer) {
                    role = "ROLE_ADMIN";
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
                }

                String token = jwtService.generateToken(foundUser.getUsername(), role);

                AuthResponseDto response = AuthResponseDto.builder()
                        .token(token)
                        .username(foundUser.getUsername())
                        .roles(List.of(role))
                        .build();

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}