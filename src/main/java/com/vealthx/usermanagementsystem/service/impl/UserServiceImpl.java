package com.vealthx.usermanagementsystem.service.impl;


import com.vealthx.usermanagementsystem.dto.UserRequest;
import com.vealthx.usermanagementsystem.dto.UserResponse;
import com.vealthx.usermanagementsystem.exception.UserNotFoundException;
import com.vealthx.usermanagementsystem.model.User;
import com.vealthx.usermanagementsystem.repository.UserRepository;
import com.vealthx.usermanagementsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper){
        this.userRepository =userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
       User user = mapper.map(userRequest, User.class);
       User savedUser = userRepository.save(user);
       return mapper.map(savedUser,UserResponse.class);

    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found with id :" +id));
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        User updatedUser = userRepository.save(user);

        return mapper.map(updatedUser, UserResponse.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
