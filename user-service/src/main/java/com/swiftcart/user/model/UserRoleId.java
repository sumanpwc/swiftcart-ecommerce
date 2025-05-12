package com.swiftcart.user.model;

import java.io.Serializable;
import java.util.UUID;
import java.util.Objects;

public class UserRoleId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4085690303283716025L;
	private UUID userId;
    private UUID roleId;
    
    UserRoleId() {
		super();
	}    
    
	public UserRoleId(UUID userId, UUID roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public UUID getRoleId() {
		return roleId;
	}
	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}   
	
	// Override equals and hashCode for proper entity comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}