package com.swiftcart.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swiftcart.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
	// Find the user based on their role
	
	@Query("SELECT u FROM User u " +
		       "JOIN FETCH u.userRoles ur " +
		       "JOIN FETCH ur.role " +
		       "WHERE u.email = :email")
	Optional<User> findByEmail(@Param("email") String email);
	
	// Get all the users based on the filtering 
	
	@Query("""
		    SELECT u FROM User u
		    JOIN u.userRoles ur
		    JOIN ur.role r
		    WHERE (:role IS NULL OR r.name = :role)
		    AND (:isEmailVerified IS NULL OR u.isEmailVerified = :isEmailVerified)
		    AND (:isPhoneVerified IS NULL OR u.isPhoneVerified = :isPhoneVerified)
		    AND (:isActive IS NULL OR u.isActive = :isActive)
		    """)
		Optional<List<User>> findFilteredUsers(@Param("role") String role,
		                              @Param("isEmailVerified") Boolean isEmailVerified,
		                              @Param("isPhoneVerified") Boolean isPhoneVerified,
		                              @Param("isActive") Boolean isActive);
	
	// Check if the User is exist or not !
	
	boolean existsByEmail(@Param("email") String email);
}