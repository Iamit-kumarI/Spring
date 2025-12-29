package com.engineeringdigest.journalAPP.service;

import com.engineeringdigest.journalAPP.entity.User;
import com.engineeringdigest.journalAPP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailserviceImpl implements UserDetailsService {
    @Autowired
    public UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user!=null){
            //to array becouse it needs comma seprated valeus
            return
                    org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))//to array becouse it needs comma seprated valeus
                    .build();
        }
        throw new UsernameNotFoundException("Username not found exception: "+username);
    }
}
