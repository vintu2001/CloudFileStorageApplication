package com.vineet.Projects.service;

import com.vineet.Projects.model.User;
import com.vineet.Projects.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    //saving the user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Fetching user by id else throwing an exception
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }

    //Updating user
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(
                        user -> {
                            user.setUsername(updatedUser.getUsername());
                            user.setPassword(updatedUser.getPassword());
                            return userRepository.save(user);
                        })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    //Fetching user with username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    //deleting user
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
