package com.noriental.security.domain;


import java.io.Serializable;

/**
 * 功能角色关联表
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public class RoleLinkFunc implements Serializable{

	private static final long serialVersionUID = 8668404732099699440L;

	// 主键
	private Long id;          
	
	// 角色主键
	private Long roleId;      
	
	// 功能主键
	private Long funcId;      

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}


}
