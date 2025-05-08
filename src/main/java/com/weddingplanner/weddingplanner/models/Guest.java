package com.weddingplanner.weddingplanner.models;

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
    private Wedding wedding;


    //@NotNull(message = "This field cannot be empty")
    @Enumerated(EnumType.STRING)
    private GuestType guestType;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean bringsCompanion;

    @ManyToOne
    @JoinColumn(name = "invited_by")
    private Couple invitedBy;

    public Guest() {
        setRole(Role.ROLE_GUEST);
    }
}
