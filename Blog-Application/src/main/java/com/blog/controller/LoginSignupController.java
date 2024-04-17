package com.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginSignupController {

    private final UserService userService;
    private final UserRepository userRepo;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User userRecord){

        User savedUserRecord = userService.addUser(userRecord);
        return new ResponseEntity<>(savedUserRecord, HttpStatus.CREATED);
    }

    @GetMapping("/signin")
    public ResponseEntity<User> login(Authentication auth){
    	
        System.out.println(auth);
        User customer= userRepo.findByEmail(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
        
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }



}
