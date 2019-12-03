package com.swe.comin.services;

import com.swe.comin.domain.dto.request.UserRequest;
import com.swe.comin.domain.dto.response.UserIdentityAvailability;
import com.swe.comin.domain.dto.response.UserProfile;
import com.swe.comin.domain.dto.response.UserSummary;
import com.swe.comin.security.UserPrincipal;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserSummary getCurrentUser(UserPrincipal currentUser);
    UserProfile getUserProfile(String username);
    UserIdentityAvailability checkUsernameAvailability(String username);
    UserIdentityAvailability checkUserEmailAvailability(String email);
    ResponseEntity<?> createUser(UserRequest userRequest);
}
