package com.noriental.security.domain;


import java.io.Serializable;


/**
 * 组人员关联表
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public class GroupLinkRole implements Serializable {

	private static final long serialVersionUID = -4839334178719430542L;
	
	private Long id;         	// 主键
	
	private Long groupId;		// 组id
	
	private Long roleId;     	// 角色id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
