package com.noriental.utils;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.noriental.security.domain.Permission;

public class AuthorizeTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String funcUri;
	private String method;
	private HttpServletRequest request;
	
	private Permission checkRequstURI(
			List<Permission> permissionList, String uri) {
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
	private boolean checkURI(String regx, String uri) {
		if(regx == null || regx.length()<=0 
				|| uri == null || uri.length()<=0) {
			return false;
		}
		// 判断URI是否相同
		if (uri.equals(regx)) {
			return true;
		}
		//是否属于正则判断
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
	public void uriToFormat() {
		if(!funcUri.endsWith("/")) {
			funcUri = funcUri + "/";
		}
	}
	public List<Permission> getPermissionList() {
		return (List<Permission>) request.getAttribute("permissionList");
	}
	//判断用户是否拥有该功能权限
	private boolean hasAuthorize() {
		//格式化请求信息
		uriToFormat();
		
		List<Permission> permissionList = getPermissionList();
		
		//判断用户是否登录	
		if(permissionList == null || permissionList.size() <= 0) {
			return false;
		}
		
		Permission functionResource = this.checkRequstURI(permissionList, funcUri);
		
		if (functionResource == null 
				|| functionResource.getMethod() == null
				|| !functionResource.getMethod().toUpperCase().contains(method.trim().toUpperCase())) {
			return false;
		}
		return true;
	}
	
	@Override
	public int doStartTag() throws JspException {
	    boolean flag = hasAuthorize();
		if(flag==true) {
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}
	
	@Override
	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getFuncUri() {
		return funcUri;
	}

	public void setFuncUri(String funcUri) {
		this.funcUri = funcUri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
