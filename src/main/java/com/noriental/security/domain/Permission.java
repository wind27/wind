package com.noriental.security.domain;


import java.io.Serializable;

/**
 * 功能资源表
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public class Permission implements Serializable {

	private static final long serialVersionUID = -2484327203479777048L;

	public static final Integer PERMISSION_FLAG_MAIN = 0;

	public static final Integer PERMISSION_FLAG_SUB = 1;

	private Long id; // 主键

	// 功能URL
	private String uri; 

	// 所属功能模块id
	private Long funcId; 

	// 状态：0、启用；1、禁用
	private Integer stats; 
	
	private String method;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public Integer getStats() {
		return stats;
	}

	public void setStats(Integer stats) {
		this.stats = stats;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
