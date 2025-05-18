package com.swiftcart.user.config;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.swiftcart.user.model.User;

public class UserPrincipal implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8922995032734070675L;
	
	private static final Logger log = LoggerFactory.getLogger(UserPrincipal.class);	
	
	private final User user;
    
    public UserPrincipal(User user) {
    	this.user = user;
    }
    
    public UUID getId() {
        return user.getId();
    }

    public String getFullName() {
        return user.getFullName();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	log.info("==================================================="+ user.getUserRoles().toString());
        return user.getUserRoles().stream()
            .map(ur -> new SimpleGrantedAuthority(ur.getRole().getName()))
            .collect(Collectors.toList());
    }
    
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; // can be extended
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getIsActive(); // deactivate = lock
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // extend for password expire logic
	}

	@Override
	public boolean isEnabled() {
		return user.getIsActive();
	}
}