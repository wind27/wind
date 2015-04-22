package com.noriental.utils;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noriental.security.domain.Permission;

/**
 * 
 * Permission 辅助类
 * @author qianchun
 * 
 * */
@Service("permissionUtils")
public class PermissionUtils {
	private static final String funcPrefix = "Permission:Group_Func:";
	private static final String permissionPrefix = "Permission:Group_Permission:";
	@Resource
	private RedisUtil redisUtil;
	
	@SuppressWarnings("unchecked")
    public List<Permission> getPermission(long groupId) {
		String key = permissionPrefix + groupId;
		return (List<Permission>) redisUtil.get(key);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getFuncIds(long groupId) {
		String key = funcPrefix + groupId;
		return (List<Long>) redisUtil.get(key);
	}
	
	
	public void setFuncIds(long groupId, List<Long> funcIds) {
	    redisUtil.setFuncIds(funcPrefix + groupId, funcIds, AuthTimeout.PERMISSION_TIMEOUT);
	}
	public void setPermission(long groupId, List<Permission> permissList) {
	    redisUtil.setPermission(permissionPrefix + groupId, permissList, AuthTimeout.PERMISSION_TIMEOUT);
	}
	
	public static class AuthTimeout {
        public static final int LOGIN_TIMEOUT = 3*60*60;
        public static final int PERMISSION_TIMEOUT = 30*24*60*60;
    }
}
