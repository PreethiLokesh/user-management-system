package com.vealthx.usermanagementsystem.controller;

import com.vealthx.usermanagementsystem.dto.UserRequest;
import com.vealthx.usermanagementsystem.dto.UserResponse;
import com.vealthx.usermanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // Create User
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest){

        UserResponse response = userService.createUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get User By Id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){

        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){

        List<UserResponse> response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @Valid @RequestBody UserRequest userRequest){

        UserResponse response = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(response);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }
}