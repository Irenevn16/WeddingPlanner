package com.weddingplanner.weddingplanner.services;

import com.weddingplanner.weddingplanner.models.Guest;
import com.weddingplanner.weddingplanner.models.Couple;
import com.weddingplanner.weddingplanner.models.WeddingOrganizer;
import com.weddingplanner.weddingplanner.models.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.findByUsername(username);
        if (optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not found: " + username);
        }
        User user = optionalUser.get();

        String role;
        if (user instanceof Guest) {
            role = "ROLE_GUEST";
        } else if (user instanceof  Couple) {
            role = "ROLE_EDITOR";
        } else if (user instanceof WeddingOrganizer) {
            role = "ROLE_ADMIN";
        } else {
            throw  new UsernameNotFoundException("Unknown user type for: " + username);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority(role))
                .build();

    }
}
