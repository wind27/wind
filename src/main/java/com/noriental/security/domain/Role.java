package com.noriental.security.domain;


import java.io.Serializable;

/**
 * 角色表.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public class Role implements Serializable {

	private static final long serialVersionUID = -466031885264644051L;

	// 状态：启用
	public static final Integer ROLE_STAT_ENABLE = 0;

	// 状态：禁用
	public static final Integer ROLE_STAT_DISABLE = 1;
	
	//超级角色类型
	public static final Integer SUPER_ROLE_TYPE = -1;

	//普通角色类型
	public static final Integer COMMON_ROLE_TYPE = 1;

	// 主键
	private Long id; 

	// 名称
	private String name; 

	// 角色类型：-1：超级角色；1、普通角色
	private Integer type; 

	// 状态：0、启用；1、禁用
	private Integer stats;
	
	private Long domainId;

	// 备注
	private String remark;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	} 
}
