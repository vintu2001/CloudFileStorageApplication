package com.vineet.Projects.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.vineet.Projects.model.User;
import com.vineet.Projects.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("securePass123");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.saveUser(user);

        assertNotNull(createdUser);
        assertEquals("john_doe", createdUser.getUsername());
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("john_doe");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = Optional.ofNullable(userService.getUserById(1L));

        assertTrue(foundUser.isPresent());
        assertEquals("john_doe", foundUser.get().getUsername());
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setUsername("user1");

        User user2 = new User();
        user2.setUsername("user2");

        List<User> userList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> retrievedUsers = userService.getAllUsers();

        assertEquals(2, retrievedUsers.size());
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);
        doNothing().when(userRepository).deleteById(userId);
        userService.deleteUser(userId);
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

}
