package com.noriental.security.domain;

import java.io.Serializable;
import java.util.Set;

import com.noriental.common.Constants.LoginType;
/**
 * 
 * 存放redis中的数据
 * 
 * 
 * */
public class User implements Serializable{
	public User() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//用户id
	private long id;
	private long msgNum;
	private Set<Long> groupIds;
	private LoginType loginType;
	private String name;
	private String schoolName;
	private String funcIds;
	private long lastestNoticeTime;
	private String loginUser;
	private long systemId;
	private String headerImg;
	private long orgId;
	private int orgType;
	private boolean hasSubject;
	private String purchaseModule;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(long msgNum) {
        this.msgNum = msgNum;
    }

    public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public Set<Long> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(Set<Long> groupIds) {
		this.groupIds = groupIds;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getFuncIds() {
        return funcIds;
    }

    public void setFuncIds(String funcIds) {
        this.funcIds = funcIds;
    }

    public long getLastestNoticeTime() {
        return lastestNoticeTime;
    }

    public void setLastestNoticeTime(long lastestNoticeTime) {
        this.lastestNoticeTime = lastestNoticeTime;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public long getSystemId() {
        return systemId;
    }

    public void setSystemId(long systemId) {
        this.systemId = systemId;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
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

    public boolean isHasSubject() {
        return hasSubject;
    }

    public void setHasSubject(boolean hasSubject) {
        this.hasSubject = hasSubject;
    }

    public String getPurchaseModule() {
        return purchaseModule;
    }

    public void setPurchaseModule(String purchaseModule) {
        this.purchaseModule = purchaseModule;
    }
}
