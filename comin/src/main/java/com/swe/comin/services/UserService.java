package com.swe.comin.services;

import com.swe.comin.controllers.dto.UserIdentityAvailability;
import com.swe.comin.controllers.dto.UserProfile;
import com.swe.comin.controllers.dto.UserResponse;
import com.swe.comin.exceptions.ResourceNotFoundException;
import com.swe.comin.models.User;
import com.swe.comin.repositories.UserRepository;
import com.swe.comin.security.UserPrincipal;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private ConfigurableConversionService conversionService;

    public UserService(UserRepository userRepository, ConfigurableConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }


    public UserIdentityAvailability checkUsernameAvailability(String username){
        return new UserIdentityAvailability(!userRepository.existsByUsername(username));
    }

    public UserIdentityAvailability checkEmailAvailability(String email){
        return new UserIdentityAvailability(!userRepository.existsByEmail(email));
    }

    public UserProfile getUserProfileByUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User does not exist."));

        return new UserProfile();
    }
    public UserResponse getCurrentUser(UserPrincipal currentUser){
        return conversionService.convert(currentUser, UserResponse.class);
    }
}
