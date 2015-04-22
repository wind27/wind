package com.noriental.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 用户cookie工具类.
 * 
 * @author 钱春
 * 
 */
public class CookieUtils {
    //登录用户id
    public static final String USER_ID = PropertiesUtil.getValue("server.properties", "cookie.login.id");
    //学校管理人员姓名
	public static final String SCHOOL_USER_NAME = "school_user_name";
	//主管方管理人员姓名
	public static final String DIRECTOR_USER_NAME = "director_user_name";
	//用户登录失败次数（教师空间）
	public static final String LOGIN_FAIL_TIMES = "e_l_t";
	
	/**
	 * 设置cookies
	 * 
	 * @author qianchun  @date 2015年2月10日 下午4:10:05
	 * @param str
	 * @param cookieName
	 * @param res
	 */
	public static void setStrCookie(String str,String cookieName,HttpServletResponse res){
		Cookie cookie = new Cookie(cookieName, str);
		cookie.setPath("/");
		cookie.setMaxAge(1*24*60*60);//记录一天
		res.addCookie(cookie);
	}
	/**
	 * 获取cookies
	 * 
	 * @author qianchun  @date 2015年2月10日 下午4:10:23
	 * @param cookieName
	 * @param request
	 * @return
	 */
	public static String getStrCookie(String cookieName,HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	/**
	 * 删除cookies
	 * 
	 * @author qianchun  @date 2015年2月10日 下午4:13:31
	 * @param cookieName
	 * @param res
	 */
	public static void removeStrCookie(String cookieName,HttpServletResponse res){
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setPath("/");
		cookie.setMaxAge(0);//立刻删除
		res.addCookie(cookie);
	}
	
	/**
	 * 设置cookies
	 * 
	 * @author qianchun  @date 2015年2月10日 下午4:12:59
	 * @param str
	 * @param cookieName
	 * @param ttl 单位秒
	 * @param res
	 */
	public static void setStrCookie(String str, String cookieName, int ttl,HttpServletResponse res){
		Cookie cookie = new Cookie(cookieName, str);
		cookie.setPath("/");
		cookie.setMaxAge(ttl);
		res.addCookie(cookie);
	}
}
