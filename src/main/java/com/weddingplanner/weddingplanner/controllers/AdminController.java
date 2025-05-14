package com.weddingplanner.weddingplanner.controllers;

import com.weddingplanner.weddingplanner.dto.WeddingDto;
import com.weddingplanner.weddingplanner.models.Couple;
import com.weddingplanner.weddingplanner.models.Wedding;
import com.weddingplanner.weddingplanner.models.WeddingOrganizer;
import com.weddingplanner.weddingplanner.repositories.CoupleRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingOrganizerRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private WeddingRepository weddingRepository;

    @Autowired
    private WeddingOrganizerRepository weddingOrganizerRepository;

    @Autowired
    private CoupleRepository coupleRepository;

    private final WeddingDto weddingDto = new WeddingDto();

    @GetMapping("/weddings")
     public List<Wedding> getAllWeddings() {
        return weddingRepository.findAll();
    }

    @PostMapping("/weddings")

    public Wedding createWedding(@RequestBody Wedding wedding) {
        return weddingRepository.save(wedding);
    }

    @PutMapping("/weddings/{id}")

    public Wedding updateWedding(@PathVariable int id, @RequestBody WeddingDto dto) {
        Wedding wedding = weddingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wedding not found with id: " + id));

        if (dto.getPlace() != null) {
            wedding.setPlace(dto.getPlace());
        }
        if (dto.getDate() != null) {
            wedding.setDate(dto.getDate());
        }
        Couple couple = coupleRepository.findById(dto.getCoupleId())
                .orElseThrow(() -> new RuntimeException("Couple not found"));
        couple.setWedding(wedding);
        coupleRepository.save(couple);

        return weddingRepository.save(wedding);
    }

    @DeleteMapping("/weddings/{id}")

    public ResponseEntity<String> deleteWedding(@PathVariable int id) {
        if (weddingRepository.existsById(id)) {
            weddingRepository.deleteById(id);
            return ResponseEntity.ok("The wedding was deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
