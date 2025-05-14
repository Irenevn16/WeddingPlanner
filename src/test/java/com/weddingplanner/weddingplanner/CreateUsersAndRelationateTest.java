package com.weddingplanner.weddingplanner;

import com.weddingplanner.weddingplanner.models.*;
import com.weddingplanner.weddingplanner.repositories.CoupleRepository;
import com.weddingplanner.weddingplanner.repositories.GuestRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingOrganizerRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CreateUsersAndRelationateTest {

    @Autowired
    CoupleRepository coupleRepository;

    @Autowired
    WeddingRepository weddingRepository;

    @Autowired
    WeddingOrganizerRepository weddingOrganizerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    GuestRepository guestRepository;

    @Test
    public void createCouple(){ //FUNCIONA
        Couple testCouple = new Couple();
        testCouple.setName("Pablo");
        testCouple.setUsername("Polxx");
        String encodedPassword = passwordEncoder.encode("123");
        testCouple.setPassword(encodedPassword);
        testCouple.setAge(43);
        coupleRepository.save(testCouple);
        //coupleRepository.delete(testCouple);
    }

    @Test
    public void createWeddingOrganizer(){ //FUNCIONA
        WeddingOrganizer testWeddingOrganizer = new WeddingOrganizer();
        testWeddingOrganizer.setName("Jimena");
        testWeddingOrganizer.setUsername("Jime9");
        String encodedPassword = passwordEncoder.encode("123");
        testWeddingOrganizer.setPassword(encodedPassword);
        testWeddingOrganizer.setAge(28);

        weddingOrganizerRepository.save(testWeddingOrganizer);
    }

    @Test
    public void createGuest(){ //FUNCIONA
        Guest testGuest = new Guest();
        testGuest.setName("Tere");
        testGuest.setUsername("T88");
        String encodedPassword = passwordEncoder.encode("123");
        testGuest.setPassword(encodedPassword);
        testGuest.setAge(79);

        Optional<Wedding> weddingOptional = weddingRepository.findById(3);
        Wedding wedding = weddingOptional.get();
        testGuest.setWedding(wedding);

        testGuest.setBringsCompanion(true);

        Optional<Couple> coupleOptional = coupleRepository.findById(602);
        Couple couple = coupleOptional.get();
        testGuest.setInvitedBy(couple);

        testGuest.setGuestType(GuestType.FRIENDS);

        guestRepository.save(testGuest);
        //guestRepository.delete(testGuest);
    }
    @Test
    public void assignWeddingToWeddingOrganizer(){
        Optional<Wedding> weddingOptional = weddingRepository.findById(1);
        Wedding wedding = weddingOptional.get();

        Optional<WeddingOrganizer> weddingOrganizerOptional = weddingOrganizerRepository.findById(252);
        WeddingOrganizer weddingOrganizer = weddingOrganizerOptional.get();

        wedding.setWeddingOrganizer(weddingOrganizer);
        weddingRepository.save(wedding);
    }

    @Test
    public void createWeddingAndAssign() { //FUNCIONA
        // Buscar la pareja por username
        Optional<Couple> coupleOpt = coupleRepository.findById(602); // el id de tu couple
        Couple couple;
        if (coupleOpt.isPresent()) {
            couple = coupleOpt.get();
        } else {
            couple = new Couple();
            couple.setName("Clara");
            couple.setUsername("CL_01");
            couple.setAge(27);
            // falta encriptar el password
            couple.setPassword("123");
            couple = coupleRepository.save(couple);

        }

        Wedding wedding = new Wedding();
        wedding.setDate(LocalDate.of(2025, 9, 5));
        wedding.setPlace("Toledo");
        wedding.setGuestList(Collections.emptyList());

        Optional<WeddingOrganizer> weddingOrganizerOptional = weddingOrganizerRepository.findById(702);
        WeddingOrganizer weddingOrganizer = weddingOrganizerOptional.get();
        wedding.setWeddingOrganizer(weddingOrganizer); // Cambia esto si tienes wedding organizer

        Wedding savedWedding = weddingRepository.save(wedding);

        couple.setWedding(savedWedding);
        coupleRepository.save(couple);
        assertNotNull(savedWedding.getId());
        assertEquals("Toledo", savedWedding.getPlace());
    }
}
