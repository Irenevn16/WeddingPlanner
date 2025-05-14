package com.weddingplanner.weddingplanner.dto;

import com.weddingplanner.weddingplanner.models.Couple;
import com.weddingplanner.weddingplanner.models.Guest;
import com.weddingplanner.weddingplanner.models.WeddingOrganizer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeddingDto {
    private int id;
    public LocalDate date;
    public String place;
    public String weddingOrganizer;
    public int coupleId;
    public List<CoupleDto> couple;
   // public List<Guest> guestList;
}
