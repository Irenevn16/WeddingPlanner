package com.weddingplanner.weddingplanner.controllers;

import com.weddingplanner.weddingplanner.dto.CoupleDto;
import com.weddingplanner.weddingplanner.dto.GuestDto;
import com.weddingplanner.weddingplanner.dto.WeddingDto;
import com.weddingplanner.weddingplanner.models.Couple;
import com.weddingplanner.weddingplanner.models.Guest;
import com.weddingplanner.weddingplanner.models.User;
import com.weddingplanner.weddingplanner.models.Wedding;
import com.weddingplanner.weddingplanner.repositories.CoupleRepository;
import com.weddingplanner.weddingplanner.repositories.GuestRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingRepository;
import com.weddingplanner.weddingplanner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/editor")

public class EditorController {
    @Autowired
    private CoupleRepository coupleRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private WeddingRepository weddingRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{coupleID}/guests")
    public ResponseEntity<List<GuestDto>> getGuests(@PathVariable int coupleId) {
        Couple couple = coupleRepository.findById(coupleId)
                .orElseThrow(() -> new RuntimeException("Couple not found with id: " + coupleId));

        Wedding wedding = couple.getWedding();
        if (wedding == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<GuestDto> guestDtos = wedding.getGuestList().stream()
                .map(guest -> new GuestDto(guest.getId(), guest.getName(), guest.getAge(), guest.getBringsCompanion()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(guestDtos);
    }


    @DeleteMapping("/{coupleId}/guests/{guestID}")
    public ResponseEntity<String> deleteGuest(@PathVariable int coupleId, @PathVariable int guestId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found with that Id"));
        if (guest.getInvitedBy().getId() != coupleId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only delete your own guests");
        }
        guestRepository.delete(guest);
        return ResponseEntity.ok("Guest deleted");
    }
    @GetMapping("/{id}/wedding") //FUNCIONA
    public WeddingDto getWeddingInfo(@PathVariable int id){
        Couple couple = coupleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couple not found with id: " + id));
        Wedding wedding = couple.getWedding();

        WeddingDto dto = new WeddingDto();
        dto.setId(wedding.getId());
        dto.setDate(wedding.getDate());
        dto.setPlace(wedding.getPlace());
        dto.setWeddingOrganizer(wedding.getWeddingOrganizer().getName());
        dto.setCoupleId(wedding.getCouple().getFirst().getId());
        //dto.setCouple(wedding.getCouple().getFirst().getUsername());


        return dto;
    }
}
