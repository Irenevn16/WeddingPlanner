package com.weddingplanner.weddingplanner.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Guest {


    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Wedding wedding;
}
