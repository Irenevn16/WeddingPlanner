package com.weddingplanner.weddingplanner;
import com.weddingplanner.weddingplanner.models.Couple;
import com.weddingplanner.weddingplanner.models.Guest;
import com.weddingplanner.weddingplanner.models.Wedding;
import com.weddingplanner.weddingplanner.repositories.CoupleRepository;
import com.weddingplanner.weddingplanner.repositories.WeddingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CoupleTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CoupleRepository coupleRepository;

    @Autowired
    private WeddingRepository weddingRepository;

    @Autowired
    private WebApplicationContext context;

    private Couple testCouple;

    private Wedding testWedding;

    public void createWedding() {
        testWedding = new Wedding();
        testWedding.setId(1);
        testWedding.setDate(LocalDate.of(2026, 05, 26));
        testWedding.setPlace("Avila");
        testWedding.setGuestList(new ArrayList<>());
        //testWedding.setWeddingOrganizer(null);
        testWedding = weddingRepository.save(testWedding);
    }

    @BeforeEach
    public void setUp(){
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    createWedding();
    testCouple = new Couple();
    testCouple.setName("Ana");
    testCouple.setAge(30);
    testCouple.setUsername("Ana1");
    testCouple.setPassword("123");
    testCouple.setRole(com.weddingplanner.weddingplanner.models.Role.ROLE_EDITOR);
    testCouple.setWedding(testWedding);

    testCouple = coupleRepository.save(testCouple);
    }

    /*@AfterEach
    public void tearDown(){
        coupleRepository.delete(testCouple);
    }*/

    @Test
    @DisplayName("Couple created correctly")
    public void createCouple(){
        System.out.println("Couple created: " + testCouple);
    }

}
