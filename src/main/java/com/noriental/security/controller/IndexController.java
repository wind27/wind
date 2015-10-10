package com.noriental.security.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.noriental.common.Constants.LoginType;
import com.noriental.security.domain.Permission;
import com.noriental.utils.LoginUserInfo;
import com.noriental.utils.LoginUserInfoUtils;
import com.noriental.utils.PermissionUtils;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private LoginUserInfoUtils loginUserInfoUtils;
    @Autowired
    private PermissionUtils permissionUtils;
	/**
     * 已登录，进入首页；否则进入登录页面
     * 
     * @author qianchun  @date 2015年2月10日 下午4:33:57
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String root(HttpServletRequest request) {
        LoginUserInfo currentUser = loginUserInfoUtils.getUser(LoginType.admin, request);
        if (currentUser == null) {
            return "/login";
        }
        
        if(currentUser!=null && currentUser.getId() > 0) {
            request.setAttribute("permissionList", getPermissList(currentUser.getGroupIds()));
        }
        return "/index";
    }
    /**
     * 跳转用户管理主页.
     * 
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        LoginUserInfo currentUser = loginUserInfoUtils.getUser(LoginType.admin, request);
        if (currentUser == null) {
            return "/login";
        }
        
        if(currentUser!=null && currentUser.getId() > 0) {
            request.setAttribute("permissionList", getPermissList(currentUser.getGroupIds()));
        }
        return "/index";
    }
    /**
     * 已登录，进入首页；否则进入登录页面
     * 
     * @author qianchun  @date 2015年2月10日 下午4:33:57
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        LoginUserInfo currentUser = loginUserInfoUtils.getUser(LoginType.admin, request);
        if (currentUser == null) {
            return "/login";
        }
        
        if(currentUser!=null && currentUser.getId() > 0) {
            request.setAttribute("permissionList", getPermissList(currentUser.getGroupIds()));
        }
        return "/index";
    }
	
	/**
	 * 根据groupIds获取权限列表
	 * 
	 * @author qianchun  @date 2015年2月10日 下午4:55:39
	 * @param groups
	 * @return
	 */
	private List<Permission> getPermissList(Set<Long> groups) {
	    List<Long> funcIds = new ArrayList<Long>();
        List<Permission> permissionList = new ArrayList<Permission>();
        for(long gid : groups) {
            List<Long> tmpIds = permissionUtils.getFuncIds(gid);
            if(tmpIds!=null && tmpIds.size()>0) {
                funcIds.addAll(tmpIds);
            }
            
            List<Permission> tmpList = permissionUtils.getPermission(gid);
            if(tmpList!=null && tmpList.size()>0) {
                permissionList.addAll(tmpList);
            }
        }
        return permissionList;
  	}
	//-------------------------------------------------------------------------------------------------------
	@RequestMapping("/websocket")
    public String websocket(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        return "/websocket";
    } 
}

