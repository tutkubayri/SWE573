package com.swe.comin.services;

import com.swe.comin.exceptions.ResourceNotFoundException;
import com.swe.comin.models.User;
import com.swe.comin.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User userRequest){
        return userRepository.save(userRequest);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(Long userId, User userRequest){
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User does not exist."));
        return userRepository.save(userRequest);
    }

    public User getByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not exist."));
    }

    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User does not exist."));
        userRepository.delete(user);
    }
}