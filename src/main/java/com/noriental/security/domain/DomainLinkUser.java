package com.noriental.security.domain;


import java.io.Serializable;

/**
 * 域-用户关系表.
 * 
 * @author 钱春
 * 
 */
public class DomainLinkUser implements Serializable {

	private static final long serialVersionUID = -4186674991178505730L;

	//主键id
	private long id;
	
	//用户id
	private long userId;
	
	//用户所属域
	private long domainId;
	
	//用户所属类型
	private int userType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getDomainId() {
		return domainId;
	}

	public void setDomainId(long domainId) {
		this.domainId = domainId;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
}
