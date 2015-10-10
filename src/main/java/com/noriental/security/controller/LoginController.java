package com.noriental.security.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noriental.common.Constants.LoginType;
import com.noriental.security.domain.Admin;
import com.noriental.security.domain.Domain;
import com.noriental.security.domain.Function;
import com.noriental.security.domain.GroupLinkUser;
import com.noriental.security.domain.Permission;
import com.noriental.security.service.AdminService;
import com.noriental.security.service.FunctionService;
import com.noriental.security.service.GroupLinkUserService;
import com.noriental.security.service.PermissionService;
import com.noriental.utils.Base64EncodeAndDecodeUtils;
import com.noriental.utils.CookieUtils;
import com.noriental.utils.Encrypt;
import com.noriental.utils.LoginUserInfo;
import com.noriental.utils.LoginUserInfoUtils;
import com.noriental.utils.PermissionUtils;
import com.noriental.utils.StrMD5;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 登录.
 * 
 * @author
 * 
 */
@Controller
@RequestMapping(value = "/")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private AdminService adminService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private GroupLinkUserService groupLinkUserService;
	@Autowired
	private ImageCaptchaService captchaService;
	   @Autowired
	    private LoginUserInfoUtils loginUserInfoUtils;
	@Autowired
    private PermissionUtils permissionUtils;
	
	/**
	 * 登录
	 * 
	 * @author qianchun  @date 2015年2月10日 下午4:34:53
	 * @param modelMap
	 * @param loginUser
	 * @param loginPass
	 * @param validateCode
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(
	        @RequestParam(value = "loginUser") String loginUser,
			@RequestParam(value = "loginPass") String loginPass,
			@RequestParam(value = "validateCode") String validateCode,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    Map<String, Object> data = new HashMap<String, Object>();
		// 判断验证码
		String captchaId = request.getSession().getId();
		boolean isResponseCorrect = false;
		try {
			if (StringUtils.isNotEmpty(validateCode)) {
				isResponseCorrect = captchaService.validateResponseForID(
						captchaId, validateCode.toLowerCase());
			}
		} catch (CaptchaServiceException e) {
			e.getMessage();
		}
		if ("performance".equals(validateCode)) { // 添加测试使用的万能验证码
			isResponseCorrect = true;
		}
		if (!isResponseCorrect) {
		    data.put("success", false);
		    data.put("code", 5);
			return data;
		}

		//判断密码是否正确
		Encrypt encrypt = new Encrypt();
		loginPass = encrypt.encrypt(loginPass, "MD5");
		try {
			loginPass = Base64.encodeBase64String(loginPass.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginUser", loginUser.toLowerCase());
		params.put("loginPass", loginPass);
		Admin admin = null;
		List<Admin> adminList = adminService.find(params);
		if (adminList == null || adminList.size() > 0) {
			admin = adminList.get(0);
		}
		// 判断用户是否启用
		if (admin == null) {
			data.put("success", false);
            data.put("code", 1);
            return data;
		} else if (admin.getStats() == Admin.ADMIN_STATS_DISABLE) { // 账户已暂停
		    data.put("success", false);
            data.put("code", 2);
            return data;
		} else if (admin.getStats() == Admin.ADMIN_STATS_ENABLE) {
			// 获取用户组
			GroupLinkUser gu = new GroupLinkUser();
			gu.setPersonId(admin.getId());
			gu.setDomainId(Domain.DOMAIN_ADMIN);
			Set<Long> groupIds = new HashSet<Long>();
			
			for(GroupLinkUser tmp : groupLinkUserService.find(gu)) {
				groupIds.add(tmp.getGroupId());
			}
			// 判断组信息是否已存储到redis中
			List<Long> funcIds = new ArrayList<Long>();
			List<Permission> permissionList = new ArrayList<Permission>();
			for(long gid : groupIds) {
				List<Long> funcIdsTmp = permissionUtils.getFuncIds(gid);
				if(funcIdsTmp==null || funcIdsTmp.size()<=0) {
					params.clear();
					params.put("stats", 0);
					params.put("personId", admin.getId());
					params.put("domainId", Domain.DOMAIN_ADMIN);
					funcIdsTmp = getfuncIdList(functionService.findByGroupId(gid));
					permissionUtils.setFuncIds(gid, funcIdsTmp);
				}
				funcIds.addAll(funcIdsTmp);
				List<Permission> permissionListTmp = permissionUtils.getPermission(gid);
				if (permissionListTmp == null || permissionListTmp.size() <= 0) {
					permissionList = permissionService.findByFuncIds(funcIdsTmp); // 获得用户能访问的所有功能资源
					permissionUtils.setPermission(gid, permissionList);
				}
			}
			if (funcIds == null || funcIds.size() <= 0) {
			    data.put("success", false);
	            data.put("code", 3);
	            return data;
			}
			// 存储用户登录信息到redis中
			LoginUserInfo user = new LoginUserInfo();
			user.setId(admin.getId());
			user.setGroupIds(groupIds);
			user.setLoginUser(admin.getLoginUser());
			user.setName(admin.getName());
			String key = new StrMD5(LoginType.admin.name() + "$"
					+ System.currentTimeMillis() + "$" + admin.getId())
					.getResult();
			key = Base64EncodeAndDecodeUtils.encoderBASE64(key);
			loginUserInfoUtils.setUser(LoginType.admin, key, user);
			CookieUtils.setStrCookie(key, CookieUtils.USER_ID, response);
			logger.info("用户登录信息--运营平台：" + admin.getLoginUser() + "(id=" + admin.getId()+ ")登录成功！！！");
			data.put("success", true);
            return data;
		}
		data.put("success", false);
        data.put("code", 6);
        return data;
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

	/**
	 * 退出系统
	 * 
	 * @author qianchun  @date 2015年2月10日 下午4:36:50
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logOut(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		LoginUserInfo currentUser = loginUserInfoUtils.getUser(LoginType.admin, request);
		if (currentUser != null) {
		    Admin admin = adminService.findById(currentUser.getId());
			CookieUtils.removeStrCookie(CookieUtils.USER_ID, response);
			loginUserInfoUtils.delUser(LoginType.admin, request);
			logger.info("用户登录信息--运营平台：" + admin.getLoginUser() + "(id=" + admin.getId()+ ")退出成功！！！");
		}
		request.setAttribute("islogin", true);
		return "/login";
	}


	public void setCaptchaService(ImageCaptchaService captchaService) {
		this.captchaService = captchaService;
	}
	public static void main(String[] args) {
	    System.out.println("用户登录信息--运营平台：" + "超级管理员" + "(" + "id=1" + ")登录成功！！！");
    }
}
