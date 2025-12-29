package com.engineeringdigest.journalAPP.controller;

import com.engineeringdigest.journalAPP.entity.User;
import com.engineeringdigest.journalAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class PublicController {
    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

}
