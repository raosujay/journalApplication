package com.sujay.journalApplication.Service;

import com.sujay.journalApplication.Entity.User;
import com.sujay.journalApplication.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd() {
        Assertions.assertEquals(4, 2+2);
        //assertEquals(4, 2+2);
    }

    @Test
    public void testFindByUserName() {
        User user = userRepository.findByUserName("Rama1");
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
           "1, 1, 2",
           "2, 10, 12",
           "3, 3, 9"
    })
    public void testAdditionWithMultipleInputs(int a, int b, int expected) {
        assertEquals(expected, a+b);
    }

    @ParameterizedTest
    @CsvSource({
            "sujay",
            "Amma",
            "Rama1"
    })
    public void testFindByUserName(String name) {
        assertNotNull(userRepository.findByUserName(name), "failed for: " + name);
    }
}
