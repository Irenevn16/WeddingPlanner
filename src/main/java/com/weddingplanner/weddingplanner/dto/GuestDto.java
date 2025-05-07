package com.weddingplanner.weddingplanner.dto;

import com.weddingplanner.weddingplanner.models.Couple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto {
    private int id;
    private String name;
    private int age;
    private String guestType;
    private Boolean bringsCompanion;
    private int weddingId;
    private Couple invitedBy;
}
