package com.vealthx.usermanagementsystem.repository;

import com.vealthx.usermanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
