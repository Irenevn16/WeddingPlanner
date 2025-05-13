package com.weddingplanner.weddingplanner.controllers;

import com.weddingplanner.weddingplanner.dto.WeddingDto;
import com.weddingplanner.weddingplanner.models.Wedding;
import com.weddingplanner.weddingplanner.models.WeddingOrganizer;
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
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private WeddingRepository weddingRepository;

    @Autowired
    private WeddingOrganizerRepository weddingOrganizerRepository;

    private final WeddingDto weddingDto = new WeddingDto();

    @GetMapping("/weddings")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Wedding> getAllWeddings() {
        return weddingRepository.findAll();
    }

    @PostMapping("/weddings")
    @PreAuthorize("hasRole('ADMIN')")
    public Wedding createWedding(@RequestBody Wedding wedding) {
        return weddingRepository.save(wedding);
    }

    @PutMapping("/weddings/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Wedding updateWedding(@PathVariable int id, @RequestBody WeddingDto dto) {
        Wedding wedding = weddingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wedding not found with id: " + id));

        wedding.setPlace(dto.getPlace());
        wedding.setDate(dto.getDate());

        return weddingRepository.save(wedding);
    }

    @DeleteMapping("/weddings/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteWedding(@PathVariable int id) {
        if (weddingRepository.existsById(id)) {
            weddingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
