package com.engineeringdigest.journalAPP.service;

import com.engineeringdigest.journalAPP.entity.User;
import com.engineeringdigest.journalAPP.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSources;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Disabled//comment this test while testing other feature of the same method
    @Test
    public void testFindByUserName(){
        User user=userRepository.findByUserName("ram");
        assertTrue(!user.getJournalEntries().isEmpty());
    }
    @Disabled
    @ParameterizedTest
    //here this value parameters a,b,expected can be passed by cavfile or just by csv
//    @CsvFileSources("filepath")
    @CsvSource({
            "1,1,2",
            "2,3,5"
    })
//    @ValueSource(Strings={
//            "Amit",
//            "Ram",
//            "Vipul"
//    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
