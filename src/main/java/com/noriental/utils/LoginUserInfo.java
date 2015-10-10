package com.noriental.utils;

import java.io.Serializable;
import java.util.Set;

import com.noriental.common.Constants.LoginType;
/**
 * 
 * 存放redis中的数据
 * 
 * 
 * */
public class LoginUserInfo implements Serializable{
	public LoginUserInfo() {
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
	private long loginTime;
	private long stageId;
	private long gradeId;
	private String stageName;
	private String gradeName;

	
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

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public long getStageId() {
        return stageId;
    }

    public void setStageId(long stageId) {
        this.stageId = stageId;
    }

    public long getGradeId() {
        return gradeId;
    }

    public void setGradeId(long gradeId) {
        this.gradeId = gradeId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
