package com.noriental.security.domain;


import java.io.Serializable;

/**
 * 功能模块信息
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public class Function implements Serializable {

	private static final long serialVersionUID = -2591321092680725655L;

	// 状态不可用
	public static final Integer FUNCTION_STATS_DISABLE = 1;

	// 状态可用
	public static final Integer FUNCTION_STATS_ENABLE = 0;
	
	//功能模块所属域
	public static final Integer FUNCTION_DOMAIN_ADMIN = 1;
	public static final Integer FUNCTION_DOMAIN_SCHOOL = 2;
	public static final Integer FUNCTION_DOMAIN_REGION = 3;

	// 主键
	private Long id; 

	// 功能模块名称
	private String name; 

	// 状态：0、启用；1、停用
	private Integer stats; 

	// 模块说明
	private String remark; 

	// 父级编号
	private Long parentCode; 
	
	// 排序字段
	private Integer _order; 
	
	//权限所属的系统：1、运营；2、学校；3、区域
	private Long domainId;	
	
	//登录用户类型
	private Integer userType;

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

	public Integer getStats() {
		return stats;
	}

	public void setStats(Integer stats) {
		this.stats = stats;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getParentCode() {
		return parentCode;
	}

	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	public Integer get_order() {
		return _order;
	}

	public void set_order(Integer _order) {
		this._order = _order;
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
