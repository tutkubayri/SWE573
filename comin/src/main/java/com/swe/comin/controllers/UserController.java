package com.swe.comin.controllers;

import com.swe.comin.models.ApiResponse;
import com.swe.comin.models.User;
import com.swe.comin.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    /*@CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        if (userService.getByUsername(newUser.getUsername()) != null) {
            logger.error("username Already exist " + newUser.getUsername());
            return new ResponseEntity(
                    new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
                    HttpStatus.CONFLICT);
        }

        return new ResponseEntity<User>(userService.saveUser(newUser), HttpStatus.CREATED);
    }*/

    @GetMapping({"/users"})
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("id/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    /*@GetMapping("id/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable Email email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }*/

    @PostMapping({"/users"})
    public ResponseEntity<User> saveUser(@RequestBody User userRequest) {
        return ResponseEntity.ok(userService.saveUser(userRequest));
    }

    @PutMapping("users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userRequest) {
        return ResponseEntity.ok(userService.updateUser(userId, userRequest));
    }

    @DeleteMapping("users/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok(new ApiResponse("User has deleted."));
    }
}