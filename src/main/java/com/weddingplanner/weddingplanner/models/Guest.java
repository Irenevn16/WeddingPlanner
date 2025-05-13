package com.weddingplanner.weddingplanner.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "guest")
@Data
@AllArgsConstructor
public class Guest extends User{

    @ManyToOne
    @JoinColumn(name = "wedding_id", nullable = false)
    @JsonBackReference
    private Wedding wedding;


    //@NotNull(message = "This field cannot be empty")
    @Enumerated(EnumType.STRING)
    private GuestType guestType;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean bringsCompanion;

    @ManyToOne
    @JoinColumn(name = "invited_by")
    @JsonBackReference
    private Couple invitedBy;

    public Guest() {
        setRole(Role.ROLE_GUEST);
    }
}
