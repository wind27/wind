package com.noriental.security.domain;


import java.io.Serializable;

/**
 * 人员组信息表
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public class Group implements Serializable {

	private static final long serialVersionUID = 4782663199619112843L;
	
	// 状态不可用
	public static final Integer GROUP_STATS_DISABLE = 1;

	// 状态可用
	public static final Integer GROUP_STATS_ENABLE = 0;

	//普通组
	public static final Integer SUPER_GROUP_TYPE = -1;

	//超级组
	public static final Integer COMMON_GROUP_TYPE = 1;
	
	private Long id; // 主键

	private String name; // 组名称

	private Integer stats; // 状态 0：启用；1：禁用

	private Integer type; // 组类型 1：超级组;1、普通组
	
	private Long domainId;

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

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
