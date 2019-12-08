package com.swe.comin.controllers;

import com.swe.comin.domain.dto.request.UserRequest;
import com.swe.comin.domain.dto.response.UserIdentityAvailability;
import com.swe.comin.domain.dto.response.UserProfile;
import com.swe.comin.domain.dto.response.UserSummary;
import com.swe.comin.security.CurrentUser;
import com.swe.comin.security.UserPrincipal;
import com.swe.comin.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return userService.getCurrentUser(currentUser);
    }

    @GetMapping("/user/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username){
        return userService.getUserProfile(username);
    }

    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        return userService.checkUsernameAvailability(username);
    }

   /* @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        return userService.checkUserEmailAvailability(email);
    }*/

    /*@PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }*/
}
