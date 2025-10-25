package com.sujay.journalApplication.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail() {
        emailService.sendEmail("sujay.92@yahoo.com",
                "Sending mail from Spring Boot JournalApplication",
                "Hi, This is a test email from sujay's JournalApplication");
    }
}