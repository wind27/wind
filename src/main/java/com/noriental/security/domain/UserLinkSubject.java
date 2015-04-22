package com.noriental.security.domain;


import java.io.Serializable;

/**
 * 组科目关系表.
 * 
 * @author 钱春
 * 
 */
public class UserLinkSubject implements Serializable {

	private static final long serialVersionUID = -4186674991178505730L;

	private Long id;               // 编号
	
	private Long domainId;

	private Long personId;          // 

	private Long subjectId;
	
	private Integer gradeType;
	
	private Integer userType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getGradeType() {
		return gradeType;
	}

	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}
