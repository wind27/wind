package com.noriental.security.domain;


import java.io.Serializable;

public class RoleVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Role role;

	private boolean isSuperRole;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isSuperRole() {
		return isSuperRole;
	}

	public void setSuperRole(boolean isSuperRole) {
		this.isSuperRole = isSuperRole;
	}
}
