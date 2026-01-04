package com.engineeringdigest.journalAPP.service;

import com.engineeringdigest.journalAPP.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.when;

//mocking the test like suppose we want test this and the requirenment like username or
//something else you can found it on db but here will make our application not to take it from
//db actually we will mock it like pretend like you are getting it from db so whenever
//our service call for requirenment will pass it here just for test
public class UserDetailserviceImplTest{
//    @InjectMocks
    @Autowired
    private UserDetailserviceImpl userDetailservice;

    @MockitoBean
    private UserRepository userRepository;

    @Disabled
    @Test
    void loadUserByUsernameTest(){
//        when(userRepository.findByUserName("ram"));//we can trick this for that we have to make our seprate test method which i haven't did
        //engineering digest Lec 25 5:00
        UserDetails user = userDetailservice.loadUserByUsername("ram");
    }
}
