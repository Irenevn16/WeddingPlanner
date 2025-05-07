package com.weddingplanner.weddingplanner.dto;

import com.weddingplanner.weddingplanner.models.Guest;
import com.weddingplanner.weddingplanner.models.WeddingOrganizer;

import java.time.LocalDate;
import java.util.List;

public class WeddingDto {
    public LocalDate date;
    public String place;
    public WeddingOrganizer weddingOrganizer;
    public List<Guest> guestList;
}
