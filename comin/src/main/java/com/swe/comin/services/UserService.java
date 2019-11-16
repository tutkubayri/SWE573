/*
package com.swe.comin.services;

import com.swe.comin.exceptions.ResourceNotFoundException;
import com.swe.comin.models.User;
import com.swe.comin.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkUsernameAndPassword(String username, String password){
        userRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new ResourceNotFoundException("User does not exist."));
    }
}*/
