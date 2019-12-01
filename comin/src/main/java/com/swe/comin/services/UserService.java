package com.swe.comin.services;

import com.swe.comin.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);

    User findByUsername(String username);
}