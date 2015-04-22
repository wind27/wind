package com.noriental.security.domain;


import java.io.Serializable;
import java.util.Date;


/**
 * 域信息表
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public class Domain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//运营所属属于域
	public static final long DOMAIN_ADMIN = 1;
	
	//公立学校所属域
	public static final long DOMAIN_PUBLIC_SCHOOL = 2;
	
	//公立主管方所属域
	public static final long DOMAIN_PUBLIC_DIRECTOR = 3;
	
	//集团学校所属域
	public static final long DOMAIN_PRIVATE_SCHOOL = 4;
	
	//集团主管方所属域
	public static final long DOMAIN_PRIVATE_DIRECTOR = 5;
	
	//状态：启用
	public static final int STATUS_ENABLE = 0;
	//状态：停用
	public static final int STATUS_DISABLE = 1;

	//主键id
	private long id;
	//名称
	private String name;
	//URI
	private String uri;
	//状态
	private int stats;
	//所属机构id
	private long orgId;
	//所属机构类型
	private int orgType;
	//备注
	private String remark;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public int getStats() {
		return stats;
	}
	public void setStats(int stats) {
		this.stats = stats;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    public long getOrgId() {
        return orgId;
    }
    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }
    public int getOrgType() {
        return orgType;
    }
    public void setOrgType(int orgType) {
        this.orgType = orgType;
    }
}
