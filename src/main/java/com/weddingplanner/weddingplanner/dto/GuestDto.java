package com.weddingplanner.weddingplanner.dto;

import com.weddingplanner.weddingplanner.models.Couple;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
    private String invitedBy;

    public GuestDto(int id, @NotEmpty(message = "This field cannot be empty") String name, @Min(value = 0, message = "Age must be a positive number") int age, Boolean bringsCompanion) {
    }
}
