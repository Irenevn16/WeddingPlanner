package com.weddingplanner.weddingplanner.controllers;

import com.weddingplanner.weddingplanner.dto.GuestDto;
import com.weddingplanner.weddingplanner.dto.WeddingDto;
import com.weddingplanner.weddingplanner.models.Guest;
import com.weddingplanner.weddingplanner.models.GuestType;
import com.weddingplanner.weddingplanner.models.Wedding;
import com.weddingplanner.weddingplanner.repositories.GuestRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/guest")
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private WeddingRepository weddingRepository;



    @GetMapping("/{id}")
    public GuestDto getGuestInfo(@PathVariable int id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));

        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        guestDto.setName(guest.getName());
        guestDto.setAge(guest.getAge());
        //guestDto.setGuestType(guest.getGuestType(GuestType.FAMILY).name());
        guestDto.setBringsCompanion(guest.getBringsCompanion());
        guestDto.setWeddingId(guest.getWedding().getId());
        guestDto.setInvitedBy(guest.getInvitedBy());

        return guestDto;
    }

    @GetMapping("/{guestID}/wedding")
    public WeddingDto getWeddingInfo(@PathVariable int guestId){
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + guestId));
        Wedding wedding = guest.getWedding();

        WeddingDto dto = new WeddingDto();
        dto.setDate(wedding.getDate());
        dto.setPlace(wedding.getPlace());
        dto.setWeddingOrganizer(wedding.getWeddingOrganizer());

        return dto;

    }

    @PatchMapping("/{id}/companion")
    public ResponseEntity<String> updateCompanion(@PathVariable int id){
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));

        guest.setBringsCompanion(false);
        guestRepository.save(guest);

        return ResponseEntity.ok("You are not bringing a companion to the wedding.");
    }
}
