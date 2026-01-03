package com.engineeringdigest.journalAPP.service;

import com.engineeringdigest.journalAPP.entity.User;
import com.engineeringdigest.journalAPP.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void testFindByUserName(){
        User user=userRepository.findByUserName("ram");
        assertTrue(!user.getJournalEntries().isEmpty());
    }
}
