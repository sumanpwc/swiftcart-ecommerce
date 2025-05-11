package com.swiftcart.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiftcart.user.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
