package com.noriental.security.domain;


import java.io.Serializable;

public class GroupVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Group group;
	//0、表示是超级组； 1、不是超级组
	private boolean isSuperGroup;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public boolean isSuperGroup() {
		return isSuperGroup;
	}

	public void setSuperGroup(boolean isSuperGroup) {
		this.isSuperGroup = isSuperGroup;
	}
}
