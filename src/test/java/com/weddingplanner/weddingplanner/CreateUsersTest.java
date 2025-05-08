package com.weddingplanner.weddingplanner;

import com.weddingplanner.weddingplanner.models.*;
import com.weddingplanner.weddingplanner.repositories.CoupleRepository;
import com.weddingplanner.weddingplanner.repositories.GuestRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingOrganizerRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CreateUsersTest {

    @Autowired
    CoupleRepository coupleRepository;

    @Autowired
    WeddingRepository weddingRepository;

    @Autowired
    WeddingOrganizerRepository weddingOrganizerRepository;

    @Autowired
    GuestRepository guestRepository;

    @Test
    public void createCouple(){ //FUNCIONA
        Couple testCouple = new Couple();
        testCouple.setName("Ana");
        testCouple.setUsername("Ann5");
        testCouple.setPassword("123");
        testCouple.setAge(36);
        coupleRepository.save(testCouple);
        coupleRepository.delete(testCouple);
    }

    @Test
    public void createWeddingOrganizer(){ //FUNCIONA
        WeddingOrganizer testWeddingOrganizer = new WeddingOrganizer();
        testWeddingOrganizer.setName("Juancho");
        testWeddingOrganizer.setUsername("Ju88");
        testWeddingOrganizer.setPassword("123");
        testWeddingOrganizer.setAge(54);

        weddingOrganizerRepository.save(testWeddingOrganizer);
    }

    @Test
    public void createGuest(){ //FUNCIONA
        Guest testGuest = new Guest();
        testGuest.setName("Mili");
        testGuest.setUsername("Mili999");
        testGuest.setPassword("123");
        testGuest.setAge(52);

        Optional<Wedding> weddingOptional = weddingRepository.findById(1);
        Wedding wedding = weddingOptional.get();
        testGuest.setWedding(wedding);

        testGuest.setBringsCompanion(true);

        Optional<Couple> coupleOptional = coupleRepository.findById(102);
        Couple couple = coupleOptional.get();
        testGuest.setInvitedBy(couple);

        testGuest.setGuestType(GuestType.FAMILY);

        guestRepository.save(testGuest);
        //guestRepository.delete(testGuest);
    }

    @Test
    public void createWeddingAndAssign() { //FUNCIONA
        // Buscar la pareja por username
        Optional<Couple> coupleOpt = coupleRepository.findById(102); // el id de tu couple
        Couple couple;
        if (coupleOpt.isPresent()) {
            couple = coupleOpt.get();
        } else {
            couple = new Couple();
            couple.setName("Pau");
            couple.setUsername("pau_01");
            couple.setPassword("1234");
            couple = coupleRepository.save(couple);
        }

        Wedding wedding = new Wedding();
        wedding.setDate(LocalDate.of(2025, 6, 15));
        wedding.setPlace("Madrid");
        wedding.setGuestList(Collections.emptyList());

        Optional<WeddingOrganizer> weddingOrganizerOptional = weddingOrganizerRepository.findById(52);
        WeddingOrganizer weddingOrganizer = weddingOrganizerOptional.get();
        wedding.setWeddingOrganizer(weddingOrganizer); // Cambia esto si tienes wedding organizer

        Wedding savedWedding = weddingRepository.save(wedding);

        couple.setWedding(savedWedding);
        coupleRepository.save(couple);
        assertNotNull(savedWedding.getId());
        assertEquals("Madrid", savedWedding.getPlace());
    }
}
