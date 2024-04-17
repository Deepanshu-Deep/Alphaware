package com.blog.service;

import com.blog.model.User;

public interface UserService{

    User addUser(User userRecord);
    User getUserByEmail(String email);

    
}
