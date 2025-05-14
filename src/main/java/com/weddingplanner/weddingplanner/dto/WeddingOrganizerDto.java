package com.weddingplanner.weddingplanner.dto;

import com.weddingplanner.weddingplanner.models.Wedding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeddingOrganizerDto {
    private int id;
    private String name;
    //public List<Wedding> weddings;
}
