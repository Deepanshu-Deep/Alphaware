package com.blog.serviceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.exception.UserException;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User addUser(User userRecord) {

        Optional<User> userExistOrNot = userRepository.findByEmail(userRecord.getEmail());

        if(userExistOrNot.isPresent())
            throw new UserException("User Already Present with email id: "+userRecord.getEmail());

        userRecord.setPassword(passwordEncoder.encode(userRecord.getPassword()));
        return userRepository.save(userRecord);
    }

    @Override
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(()->new UserException("No user found with email: "+email));
    }


}
