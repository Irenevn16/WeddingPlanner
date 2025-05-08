package com.weddingplanner.weddingplanner;
import com.weddingplanner.weddingplanner.models.Couple;
import com.weddingplanner.weddingplanner.models.Role;
import com.weddingplanner.weddingplanner.repositories.CoupleRepository;
import org.junit.jupiter.api.BeforeEach;

//Junit

public class CoupleRepositoryTest {
    private Couple couple;
    private CoupleRepository coupleRepository;

    @BeforeEach
    public void setUp() {
        couple = new Couple();
        couple.setName("Ana");
        couple.setAge(28);
        couple.setUsername("Ana1");
        couple.setPassword("1234");
        couple.setRole(Role.ROLE_EDITOR);


    }
}
