package com.noriental.security.domain;


import java.io.Serializable;
import java.util.Date;


/**
 * 人员信息表
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public class Admin implements Serializable {

	private static final long serialVersionUID = -4893971143218206375L;

	// 状态启用
	public static final int ADMIN_STATS_ENABLE = 0;

	// 状态停用
	public static final int ADMIN_STATS_DISABLE = 1;

	//超级管理员类型
	public static final int SUPER_ADMIN_TYPE = -1;

	//普通用户类型
	public static final int COMMON_ADMIN_TYPE = 1;
	
	//用户类型：1
	public static final int USER_TYPE=1;
	
	private Long id;

	// 姓名
	private String name; 

	// 电话
	private String phone; 

	// 邮箱
	private String email; 

	// qq
	private String qq; 

	// 用户名
	private String loginUser; 

	// 密码
	private String loginPass; 

	//0：启用；1：停用
	private Integer stats;  

	//-1:超级管理员；1、普通用户
	private Integer type; 

	private Date createTime;
	
	private Date updateTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public Integer getStats() {
		return stats;
	}

	public void setStats(Integer stats) {
		this.stats = stats;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
