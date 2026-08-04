package com.vealthx.usermanagementsystem.service;

import com.vealthx.usermanagementsystem.dto.UserRequest;
import com.vealthx.usermanagementsystem.dto.UserResponse;

import java.util.List;

public interface UserService {

   UserResponse createUser(UserRequest userRequest);
   UserResponse getUserById(Long id);
   List<UserResponse> getAllUsers();
   UserResponse updateUser(Long id, UserRequest userRequest);
   void deleteUser(Long id);
}
