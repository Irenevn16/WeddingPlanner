package com.weddingplanner.weddingplanner.controllers;

import com.weddingplanner.weddingplanner.dto.CoupleDto;
import com.weddingplanner.weddingplanner.dto.GuestDto;
import com.weddingplanner.weddingplanner.dto.WeddingDto;
import com.weddingplanner.weddingplanner.models.Guest;
import com.weddingplanner.weddingplanner.models.GuestType;
import com.weddingplanner.weddingplanner.models.Wedding;
import com.weddingplanner.weddingplanner.repositories.CoupleRepository;
import com.weddingplanner.weddingplanner.repositories.GuestRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/guest")
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private WeddingRepository weddingRepository;

    @Autowired
    private CoupleRepository coupleRepository;


    @GetMapping("/{id}") //FUNCIONA
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
        guestDto.setInvitedBy(guest.getInvitedBy().getName());

        return guestDto;
    }

    @GetMapping("/{id}/wedding") //FUNCIONA
    public WeddingDto getWeddingInfo(@PathVariable int id){
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));
        Wedding wedding = guest.getWedding();

        List<CoupleDto> coupleDtos = wedding.getCouple().stream()
                .map(couple -> {
                    CoupleDto dto = new CoupleDto();
                    dto.setId(couple.getId());
                    dto.setName(couple.getName());
                    return dto;
                } )
                .collect(Collectors.toList());

        WeddingDto dto = new WeddingDto();
        dto.setId(wedding.getId());
        dto.setDate(wedding.getDate());
        dto.setPlace(wedding.getPlace());
        dto.setCouple(coupleDtos);

        return dto;

    }

    @PatchMapping("/{id}/companion") //FUNCIONA
    public ResponseEntity<String> updateCompanion(@PathVariable int id){
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));

        guest.setBringsCompanion(false);
        guestRepository.save(guest);

        return ResponseEntity.ok("You are not bringing a companion to the wedding.");
    }
}
