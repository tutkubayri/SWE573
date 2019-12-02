package com.swe.comin.controllers;

import com.swe.comin.controllers.dto.UserIdentityAvailability;
import com.swe.comin.controllers.dto.UserProfile;
import com.swe.comin.controllers.dto.UserResponse;
import com.swe.comin.security.CurrentUser;
import com.swe.comin.security.UserPrincipal;
import com.swe.comin.services.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/me")
    public UserResponse getCurrentUser(@CurrentUser UserPrincipal currentUser){
        return userService.getCurrentUser(currentUser);
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam String username){
        return userService.checkUsernameAvailability(username);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam String email){
        return userService.checkEmailAvailability(email);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable String username) {
        return userService.getUserProfileByUsername(username);
    }

}
