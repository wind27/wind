package com.noriental.security.domain;


import java.io.Serializable;


/**
 * 组人员关联表
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public class GroupLinkUser implements Serializable {

	private static final long serialVersionUID = -4839334178719430542L;
	
	// 主键
	private Long id;         
	
	// 组编号
	private Long groupId;	
	
	// 人员编号
	private Long personId;     
	
	//登录用户类型
	private Integer userType;
	
	//域id
	private Long domainId;

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

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}
}
