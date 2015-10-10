package com.noriental.security.interceptor;

/**
 * @Description:
 * @cta-new.com.cqa.cms.interceptors
 * @FileName:AuthorizeInterceptor.java
 * @Created By:qc
 * @Created:2011-9-7 下午01:53:52
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.noriental.common.Constants.LoginType;
import com.noriental.security.domain.Function;
import com.noriental.security.domain.Permission;
import com.noriental.security.service.FunctionService;
import com.noriental.security.service.PermissionService;
import com.noriental.utils.LoginUserInfo;
import com.noriental.utils.LoginUserInfoUtils;
import com.noriental.utils.PermissionUtils;
import com.noriental.utils.StringUtil;

/**
 * 拦截器
 * 
 * @author qianchun
 * @date 2015年2月10日 下午4:18:07
 */
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizeInterceptor.class);
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private FunctionService functionService;
	@Autowired
	private LoginUserInfoUtils loginUserInfoUtils;
	@Autowired
    private PermissionUtils permissionUtils;

	private List<String> whiteList;

	public void setWhiteList(List<String> whiteList) {
		this.whiteList = whiteList;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获得访问URI, 并格式化URI
		String uri = request.getRequestURI();
		uri = formatURI(uri, request.getContextPath());
		
		//判断是否允许访问
		LoginUserInfo currentUser = loginUserInfoUtils.getUser(LoginType.admin, request);
		List<Permission> permissionList = null;
		
		//判断是否属于白名单
		if (checkWhiteList(uri)) {
		    if(currentUser!=null && currentUser.getId() > 0) {
		        request.setAttribute("islogin", true);
		        request.setAttribute("loginUserName", currentUser.getName());
		        permissionList = getPermissList(currentUser.getGroupIds());
		        request.setAttribute("permissionList", permissionList);
		    } else {
		        request.setAttribute("islogin", false);
		    }
		    return super.preHandle(request, response, handler);
		}
		if (currentUser == null || currentUser.getId()<=0) {					//用户未登录，或者超时(memcached中的信息也失效)
			logger.warn("IP: " + request.getRemoteAddr() + " Invalid Access"+",uri:"+uri);
			response.sendError(401);
			request.setAttribute("islogin", false);
			return false;
		} else {
            request.setAttribute("islogin", true);
            request.setAttribute("loginUserName", currentUser.getName());
            permissionList = getPermissList(currentUser.getGroupIds());
            request.setAttribute("permissionList", permissionList);
		}
		

		logger.info("["+currentUser.getId()+"] "+currentUser.getName() + "--------- "+uri);
		// 获得用户有权访问的所有资源.
		if (permissionList == null || permissionList.size() == 0) {
			logger.warn("IP: " + request.getRemoteAddr() + " LoginUser: " + currentUser.getName() + " Invalid permissions");
			response.sendError(403);
			return false;
		}

		// 检查访问权限.
		Permission permission = this.checkRequstURI(permissionList, uri, request.getMethod());
		if (permission == null) {
			logger.warn("IP: " + request.getRemoteAddr() + " LoginUser: "
					+ currentUser.getLoginUser() + " Invalid permissions");
			logger.warn("uri : " + uri + " method : "+ request.getMethod() + "*** not premission !!!");
			response.sendError(403);
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	/**
	 * 检查白名单
	 * 
	 * @param uri
	 * @return
	 */
	private Boolean checkWhiteList(String uri) {
		if (whiteList != null && whiteList.size() > 0) {
			for (String white : whiteList) {
				if (checkURIWhiteList(white, uri)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean checkURI(String regx, String uri) {
		if(regx == null || regx.length()<=0 
				|| uri == null || uri.length()<=0) {
			return false;
		}
		// 判断URI是否相同
		if (uri.equals(regx)) {
			return true;
		}
		if(regx.contains("\\d") || regx.contains("\\w")) {
			if(StringUtil.regex(uri, regx)) {
				return true;
			}
		}
		// 判断是否有参数
		int i = regx.indexOf("{");
		// 判断是否除参数外的URI开头相同
		
		
		if (i > 0 && uri.startsWith(regx.substring(0, i))) {
			// 如果开头相同判断参数个数是否相同
			if (regx.split("/").length == uri.split("/").length) {
				return true;
			}
		}

		return false;
	}
	private boolean checkURIWhiteList(String regx, String uri) {
		if(regx == null || regx.length()<=0 
				|| uri == null || uri.length()<=0) {
			return false;
		}
		// 判断URI是否相同
		if (uri.equals(regx)) {
			return true;
		}
		if(regx.contains("\\d") || regx.contains("\\w")) {
			if(StringUtil.regex(uri, regx)) {
				return true;
			}
		}
		// 判断是否有参数
		int i = regx.indexOf("{");
		
		if (i > 0 && uri.startsWith(regx.substring(0, i))) {
			// 如果开头相同判断参数个数是否相同
			if (regx.split("/").length == uri.split("/").length) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 检测是否有访问权限.
	 * 
	 * @param funcResourceInfos
	 * @param uri
	 * @return
	 */
	private Permission checkRequstURI(
			List<Permission> permissionList, String uri, String method) {
		for (Permission permission : permissionList) {
			if(this.checkURI(permission.getUri(), uri)) {
				if(permission.getMethod()==null || permission.getMethod().equals("")) {
					return permission;
				} else if(permission.getMethod().trim().toUpperCase().equals(method)){
					return permission;
				}
			}
		}
		return null;
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
            List<Long> tmpFuncIds = permissionUtils.getFuncIds(gid);
            if(tmpFuncIds!=null && tmpFuncIds.size()>0) {
                funcIds.addAll(tmpFuncIds);
            } else {
                tmpFuncIds = getfuncIdList(functionService.findByGroupId(gid));
                permissionUtils.setFuncIds(gid, tmpFuncIds);
            }
            
            List<Permission> tmpPermissionList = permissionUtils.getPermission(gid);
            if(tmpPermissionList!=null && tmpPermissionList.size()>0) {
                permissionList.addAll(tmpPermissionList);
            } else {
                tmpPermissionList = permissionService.findByFuncIds(tmpFuncIds);
                permissionUtils.setPermission(gid, tmpPermissionList);
            }
        }
        return permissionList;
    }
    /**
     * 获得功能信息集合的所有ID.
     * 
     * @author qianchun  @date 2015年2月10日 下午4:36:32
     * @param functionList
     * @return
     */
    private List<Long> getfuncIdList(List<Function> functionList) {
        List<Long> funcIdList = new ArrayList<Long>();
        for (Function function : functionList) {
            funcIdList.add(function.getId());
        }
        return funcIdList;
    }
    public static String formatURI(String uri, String contextPath) {
        // 去应用名
        uri = uri.substring(contextPath.length());

        // 去参数
        if (uri.indexOf("?") > -1) {
            String[] temp = uri.split("?");
            if (temp != null && temp.length > 1) {
                uri = temp[0];
            }
        }

        // 结尾补/
        if (!uri.endsWith("/")) {
            uri += "/";
        }

        return uri;
    }
}