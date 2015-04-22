package com.noriental.utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.noriental.common.Constants.LoginType;
import com.noriental.security.domain.User;
import com.noriental.utils.PermissionUtils.AuthTimeout;

/**
 * 
 * User 辅助类
 * @author qianchun
 * 
 * */
@Service
public class UserUtils {
	private static final String userPrefix = "LoginUser:";
	@Resource
	private RedisUtil redisUtil;
	
	/**
	 * 
	 * 获取redis中登录用户信息
	 * 
	 * @author qianchun
	 * 
	 * @param portal	:	所属系统
	 * 
	 * @return : 返回获取的结果
	 * 
	 * */
	public User getUser(LoginType portal, HttpServletRequest request) {
		String cookieName = CookieUtils.getStrCookie(CookieUtils.USER_ID, request);
		cookieName = (userPrefix + portal+":"+cookieName);
		if(!StringUtils.isBlank(cookieName)) {
			return  (User) redisUtil.getLoginUser(cookieName);
		} 
		return null;
	}
	
	/**
	 * 
	 * 设置redis中登录用户信息
	 * 
	 * @author qianchun
	 * 
	 * @param portal	:	所属系统
     * @param user 	    :	用户信息
     * 
     * @return : 返回设置结果
	 * 
	 * */
	public boolean setUser(LoginType portal, String key, User user) {
		key = (userPrefix + portal+":"+key);
		if(!StringUtils.isBlank(key)) {
			return redisUtil.setLoginUser(key, user, AuthTimeout.LOGIN_TIMEOUT) == 1 ? true : false;
		}
		return false;
	}
	/**
	 * 
	 * 设置redis中登录用户信息
	 * 
	 * @author qianchun
	 * @param portal	:	所属系统
	 * @param user      :   用户信息
	 * @param during 	:	设置的生命周期
	 * 
	 * @return : 返回设置结果
	 * 
	 * */
	public boolean setUser(LoginType portal, String key, User user, int during) {
	    key = (userPrefix + portal+":"+key);
	    if(!StringUtils.isBlank(key)) {
	        return redisUtil.setLoginUser(key, user, during) == 1 ? true : false;
	    }
	    return false;
	}

	/**
	 * 
	 * 重新设置redis中登录用户信息的生命周期
	 * 
	 * @author qianchun
	 * 
	 * @param portal	:	所属系统
     * @param during 	:	设置的生命周期
     * 
     * @return : 返回设置结果
	 * 
	 * */
	public boolean resetUserDuring(LoginType portal, HttpServletRequest request) {
		String cookieName = CookieUtils.getStrCookie(CookieUtils.USER_ID, request);
		cookieName = (userPrefix + portal+":"+cookieName);
		if(!StringUtils.isBlank(cookieName)) {
			return redisUtil.resetLoginUserDuring(cookieName, AuthTimeout.LOGIN_TIMEOUT);
		}
		return false;
	}

	/**
	 * 
	 * 删除redis中登录用户信息
	 * 
	 * @author qianchun
	 * 
	 * @param portal	:	所属系统
     * 
     * @return : 返回设置结果
	 * 
	 * */
	public boolean delUser(LoginType portal, HttpServletRequest request) {
		String cookieName = CookieUtils.getStrCookie(CookieUtils.USER_ID, request);
		cookieName = userPrefix + portal + ":" + cookieName;
		if(!StringUtils.isBlank(cookieName)) {
			return redisUtil.delLoginUser(cookieName);
		}
		return false;
	}
}
