package com.weddingplanner.weddingplanner;

import com.weddingplanner.weddingplanner.models.Couple;
import com.weddingplanner.weddingplanner.repositories.CoupleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PruebaTest {

    @Autowired
    CoupleRepository coupleRepository;

    @Test
    public void createCouple(){
        Couple testCouple = new Couple();
        testCouple.setName("Mol");
        testCouple.setUsername("Mol1");
        testCouple.setPassword("123");
        testCouple.setAge(25);
        coupleRepository.save(testCouple);
    }
}
