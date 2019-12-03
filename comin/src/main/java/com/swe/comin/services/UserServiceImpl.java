package com.swe.comin.services;

import com.swe.comin.domain.dto.request.UserRequest;
import com.swe.comin.domain.dto.response.UserIdentityAvailability;
import com.swe.comin.domain.dto.response.UserProfile;
import com.swe.comin.domain.dto.response.UserSummary;
import com.swe.comin.domain.model.Role;
import com.swe.comin.domain.model.User;
import com.swe.comin.domain.repository.RoleRepository;
import com.swe.comin.domain.repository.UserRepository;
import com.swe.comin.exceptions.UserAlreadyInUseException;
import com.swe.comin.models.ApiResponse;
import com.swe.comin.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserSummary getCurrentUser(UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getEmail());
    }

    @Override
    public UserProfile getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserProfile(user.getId(), user.getUsername(), user.getEmail());
    }

    @Override
    public UserIdentityAvailability checkUsernameAvailability(String username) {
        return new UserIdentityAvailability(!userRepository.existsByUsername(username));
    }

    @Override
    public UserIdentityAvailability checkUserEmailAvailability(String email) {
        return new UserIdentityAvailability(!userRepository.existsByEmail(email));
    }

    @Override
    public ResponseEntity<?> createUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername()) || userRepository.existsByEmail(userRequest.getEmail())) {
            throw new UserAlreadyInUseException("User is already in use!");
        }

        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .roles(Collections.singleton(roleRepository.findByName(Role.RoleName.ROLE_USER)))
                .roles(Collections.singleton(roleRepository.findByName(userRequest.getRole())))
                .build();

        userRepository.save(user);
        return ResponseEntity.ok().body(new ApiResponse(true, "User created."));
    }
}
