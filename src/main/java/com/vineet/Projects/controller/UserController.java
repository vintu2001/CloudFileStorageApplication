package com.vineet.Projects.controller;

import com.vineet.Projects.model.User;
import com.vineet.Projects.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    //creating new user
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    //getting user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //searching user by username
    @GetMapping("/search")
    public Optional<User> getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    //Update user by id
    @PostMapping("/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User user){
        return userService.updateUser(id,user);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully.";
    }
}
