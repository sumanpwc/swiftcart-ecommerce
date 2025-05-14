package com.swiftcart.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swiftcart.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
	@Query("SELECT u FROM User u " +
		       "JOIN FETCH u.userRoles ur " +
		       "JOIN FETCH ur.role " +
		       "WHERE u.email = :email")
	Optional<User> findByEmail(@Param("email") String email);
}
