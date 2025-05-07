package com.weddingplanner.weddingplanner.controllers;

import com.weddingplanner.weddingplanner.dto.WeddingDto;
import com.weddingplanner.weddingplanner.models.Wedding;
import com.weddingplanner.weddingplanner.models.WeddingOrganizer;
import com.weddingplanner.weddingplanner.repositories.WeddingOrganizerRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private WeddingRepository weddingRepository;

    @Autowired
    private WeddingOrganizerRepository weddingOrganizerRepository;

    private final WeddingDto weddingDto = new WeddingDto();

    @GetMapping("/weddings")
    public List<Wedding> getAllWeddings(){
        return weddingRepository.findAll();
    }

    @PostMapping("/weddings")
    public Wedding createWedding(@RequestBody Wedding wedding) {
        return weddingRepository.save(wedding);
    }

    @PutMapping("/weddings/{id}")
    public Wedding updateWedding(@PathVariable int id, @RequestBody WeddingDto dto) {
        Wedding wedding =  weddingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wedding not found with id: " + id));

        wedding.setPlace(weddingDto.getPlace());
        wedding.setDate(weddingDto.getDate());

        return weddingRepository.save(wedding);
    }

    @DeleteMapping("/weddings/{id}")
    public void deleteWedding(@PathVariable int id) {
        weddingRepository.deleteById(id);
        System.out.println("The wedding was deleted");
    }
}
