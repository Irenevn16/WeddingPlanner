package com.weddingplanner.weddingplanner.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "couple")
@Data
@AllArgsConstructor

public class Couple extends User {

    @ManyToOne
    @JoinColumn(name = "wedding_id", nullable = false)
    private Wedding wedding;

    /*@OneToMany(mappedBy = "invitedBy")//, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Guest> guests;*/

    public Couple(){
        setRole(Role.ROLE_EDITOR);
    }
}
