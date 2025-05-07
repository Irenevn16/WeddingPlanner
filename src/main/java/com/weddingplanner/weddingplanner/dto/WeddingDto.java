package com.weddingplanner.weddingplanner.dto;

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
    public LocalDate date;
    public String place;
    public WeddingOrganizer weddingOrganizer;
    public List<Guest> guestList;
}
