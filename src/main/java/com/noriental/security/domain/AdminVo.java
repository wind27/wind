package com.noriental.security.domain;


import java.io.Serializable;

public class AdminVo implements Serializable {
	private static final long serialVersionUID = 1L;

	//用户信息
	private Admin admin;
	
	//用户所属组
	private Group group;
	
	//组名
	private String groupName;
	
	//是否超级用户
	private boolean isSuper;
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public boolean isSuper() {
		return isSuper;
	}

	public void setSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}
}
