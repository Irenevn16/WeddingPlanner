package com.weddingplanner.weddingplanner.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.time.LocalDate;
@Entity
@Table(name = "wedding")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Column(nullable = false)
    private String place;

    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Guest> guestList;

    @ManyToOne
    @JoinColumn(name = "wedding_organizer_id")
    @JsonBackReference // No mostrará al wedding organizer en json
    private WeddingOrganizer weddingOrganizer;
}
