package com.weddingplanner.weddingplanner.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "wedding_organizer")
public class WeddingOrganizer extends User {

    @OneToMany(mappedBy = "weddingOrganizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wedding> weddings;

    public WeddingOrganizer(){
        setRole(Role.ROLE_ADMIN);
    }
}
