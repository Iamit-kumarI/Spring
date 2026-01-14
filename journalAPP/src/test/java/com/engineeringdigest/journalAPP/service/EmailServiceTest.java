package com.engineeringdigest.journalAPP.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail(){
        emailService.sendEmail(
                "mr.hunterritik@gmail.com",
                "testing email services",
                "I am eamil body");
    }
}
