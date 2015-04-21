package com.noriental.security.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.PageRanges;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.noriental.global.Constants.PagerSize;
import com.noriental.global.dict.AppType.LoginType;
import com.noriental.security.domain.User;
import com.noriental.security.usermanage.domain.Admin;
import com.noriental.security.usermanage.domain.Domain;
import com.noriental.security.usermanage.domain.Function;
import com.noriental.security.usermanage.domain.Group;
import com.noriental.security.usermanage.domain.GroupLinkRole;
import com.noriental.security.usermanage.domain.Permission;
import com.noriental.security.usermanage.domain.Role;
import com.noriental.security.usermanage.domain.RoleVo;
import com.noriental.security.usermanage.service.FunctionService;
import com.noriental.security.usermanage.service.GroupLinkRoleService;
import com.noriental.security.usermanage.service.GroupService;
import com.noriental.security.usermanage.service.PermissionService;
import com.noriental.security.usermanage.service.RoleService;
import com.noriental.security.utils.PermissionUtils;
import com.noriental.security.utils.UserUtils;
import com.sumory.mybatis.pagination.result.PageResult;



@Controller
@RequestMapping("/user")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private GroupLinkRoleService groupLinkRoleService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserUtils userUtils;
	@Autowired
    private PermissionUtils permissionUtils;
	/**
     * 
     * 打开添加角色页面
     * 
     * */
    @RequestMapping("/openAddRole")
    public String openAddRole(ModelMap model, HttpServletRequest request) {
    	//存储一级权限信息
    	Map<Function, Map<Function, List<Function>>> firstFuncMap = 
    			getFuncMap();
    	
    	model.addAttribute("qroleName", request.getParameter("qroleName")==null?-1:request.getParameter("qroleName"));
        model.addAttribute("start", request.getParameter("start")==null?"":request.getParameter("start"));
        
    	model.addAttribute("funcMap", firstFuncMap);
        return "/user/addRole";
    }
    
	/**
     * 
     * 添加角色
     * 
     * */
    @ResponseBody
    @RequestMapping(value="/doAddRole", method = RequestMethod.POST)
    public boolean  doAddRole(ModelMap model, 
            @RequestParam(value = "funclist", required = false) Long[] funclist,
            Group group, HttpServletRequest request) {
    	Role role = new Role();
    	role.setName(group.getName());
    	role.setRemark(group.getRemark());
    	role.setDomainId(Domain.DOMAIN_ADMIN);
    	role.setType(Role.COMMON_ROLE_TYPE);
    	role.setStats(Role.ROLE_STAT_ENABLE);
    	
    	model.addAttribute("qroleName", request.getParameter("qroleName")==null?-1:request.getParameter("qroleName"));
        model.addAttribute("start", request.getParameter("start")==null?"":request.getParameter("start"));
        
    	return roleService.create(role, funclist);
    }
    
    
    /**
     * 
     * 打开列表
     * 
     * */
    @RequestMapping(value="/toRoleList", method=RequestMethod.GET)
    public String toRoleListPage(ModelMap model,
    		@RequestParam(value="pageNo", defaultValue="1", required=false) int pageNo,
    		HttpServletRequest request) {
        model.addAttribute("pageNo", pageNo);
    	return "/user/roleList";
    }
    /**
     * 
     * 打开查看角色信息页面
     * 
     * */
    @RequestMapping("/showRole")
    public String showRole(ModelMap model, 
    		@RequestParam("roleId") Long roleId,
    		HttpServletRequest request) {
    	Role role = roleService.findById(roleId);
    	List<Long> funcIds = new ArrayList<Long>();
    	for(Function func : functionService.findByRoleId(role.getId())) {
    		funcIds.add(func.getId());
    	}
    	
    	//存储一级权限信息
    	Map<Function, Map<Function, List<Function>>> firstFuncMap = 
    			getFuncMap();
    	
    	model.addAttribute("name", role.getName());
    	model.addAttribute("remark", cutTooLongString(role.getRemark()));
    	model.addAttribute("remark", role.getRemark());
    	model.addAttribute("funcMap", firstFuncMap);
    	model.addAttribute("funcIds", funcIds);
    	
    	return "/user/showRole";
    }
    
    /**
     * 
     * 打开编辑角色页面
     * 
     * */
    @RequestMapping("/openUpdateRole")
    public String openUpdateRole(ModelMap model, 
    		@RequestParam("roleId") Long roleId,
    		HttpServletRequest request) {
    	Role role = roleService.findById(roleId);
    	List<Long> funcIds = new ArrayList<Long>();
    	for(Function func : functionService.findByRoleId(role.getId())) {
    		funcIds.add(func.getId());
    	}
    	Map<Function, Map<Function, List<Function>>> firstFuncMap = 
    			getFuncMap();
    	
    	model.addAttribute("role", role);
    	model.addAttribute("funcMap", firstFuncMap);
    	model.addAttribute("funcIds", funcIds);
        return "/user/updateRole";
    }
    /**
     * 
     * 更新角色信息
     * 
     * */
    @ResponseBody
    @RequestMapping(value="/doUpdateRole", method=RequestMethod.POST)
    public Object doUpdateRole(ModelMap model, 
            Role role,
            @RequestParam(value = "funclist", required = false) Long[] funclist,
            HttpServletRequest request) {
    	boolean flag = roleService.updateRole(role, funclist);

    	List<GroupLinkRole> grList = groupLinkRoleService.findByRoleId(role.getId());
    	
    	//判断组信息是否已存储到redis中
		List<Function> funcList = null;
		List<Long> funcIds = null;
		List<Permission> permissList = null;
		
		for(GroupLinkRole gr : grList) {
			funcList = functionService.findByGroupId(gr.getGroupId());
			funcIds = getfuncIdList(funcList);
			permissList = permissionService.findByFuncIds(funcIds);			// 获得用户能访问的所有功能资源
			
			permissionUtils.setFuncIds(gr.getGroupId(), funcIds);
			permissionUtils.setPermission(gr.getGroupId(), permissList);
		}
		Map<String, Object> data = new HashMap<String, Object>();
    	data.put("success", flag);
		return data;
    }
    
    /**
     * 
     * 判断待新增的组名是否已存在
     * 
     * */
    @ResponseBody
    @RequestMapping("/valRoleName")
    public Boolean valGroupName(@RequestParam("rolename") String rolename, HttpSession session)
            throws UnsupportedEncodingException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", rolename.trim());
        List<Role> roleList = roleService.find(params);
        if(roleList!=null && roleList.size()>0) {
        	return false;
        }
        return true;
    }
	/**
	 * 
	 * 删除组
	 * 
	 * */
	@ResponseBody
	@RequestMapping("/doDeleteRole")
    public boolean doDeleteRole(@RequestParam("roleId") Long roleId, HttpSession session)
            throws UnsupportedEncodingException {
		return roleService.delete(roleId);
    }
	/**
	 * 
	 * 判断待删除的角色中是否还有组
	 * 
	 * */
	@ResponseBody
    @RequestMapping("/hasRoleInGroup")
    public boolean hasRoleInGroup(@RequestParam("roleId") Long roleId, HttpSession session)
            throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		
		List<GroupLinkRole> grList = groupLinkRoleService.findByRoleId(roleId);
		if(grList != null && grList.size()>0) {
			return true;
		}
		
        return false;
    }
	//----------------------------------------------------------------------------------------------
	/**
     * 
     * 截取字符串的前20个字符
     * 
     * */
    private String cutTooLongString(String str) {
    	if(str == null || str.length()<=0) return str;
		int len = 0;
		for(int i=0;i<str.length();i++){     
			if(str.substring(i, i+1).matches("[\\u4e00-\\u9fbb]+")) {
				len += 2;
			} else {
				len += 1;
			}
			if(len >= 20) {
				if(str.substring(0, i+1).contains(str)==false) {
					str = str.substring(0, i+1)+"...";
				}
				break;
			}
		} 
    	return str;
    }
	/**
	 * 
	 * 获取一级、二级、三级权限
	 * 
	 * */
	public Map<Function, Map<Function, List<Function>>> getFuncMap() {
		//存储一级权限信息
    	Map<Function, Map<Function, List<Function>>> firstFuncMap = 
    			new LinkedHashMap<Function, Map<Function, List<Function>>>();
    	
    	//存储二级权限信息
    	Map<Function, List<Function>> secondFuncMap = new LinkedHashMap<Function, List<Function>>();
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("parentCode", 4);
    	params.put("stats", Function.FUNCTION_STATS_ENABLE);
    	params.put("domain", Function.FUNCTION_DOMAIN_ADMIN);
    	params.put("userType", 1);
    	
    	
    	List<Function> firstFuncList = functionService.find(params);
    	
    	//移除权限功能模块
    	if(firstFuncList!=null && firstFuncList.size()>0) {
    		for(int i=0; i<firstFuncList.size(); i++) {
    			Function func = firstFuncList.get(i);
    			if(func!=null && func.getId() == 5) {
    				firstFuncList.remove(i);
    				i --;
    			}
    		}
    	}
    	
    	List<Function> secondFuncList = null;
    	List<Function> thirdFuncList = null;
    	
		if(firstFuncList!=null && firstFuncList.size()>0) {
			for(Function firstFunc : firstFuncList) {
				secondFuncMap = new LinkedHashMap<Function, List<Function>>();
				params.clear();
		    	params.put("parentCode", firstFunc.getId());
		    	params.put("stats", 0);
				secondFuncList = functionService.find(params);
				
				for(Function secondFunc : secondFuncList) {
					params.clear();
			    	params.put("parentCode", secondFunc.getId());
			    	params.put("stats", 0);
					thirdFuncList = functionService.find(params);
					secondFuncMap.put(secondFunc, thirdFuncList);
				}
				
				firstFuncMap.put(firstFunc, secondFuncMap);
			}
		}
		return firstFuncMap;
	}
	//分页--------------------------------------------------------------------------------
    /**
     * 
     * 打开用户列表
     * 
     * */
    @RequestMapping(value="/toRoleList", method=RequestMethod.POST,headers = {"Accept=application/json"})
    public @ResponseBody
    	Object toRoleList(ModelMap model, 
	    	@RequestParam(value = "roleName", required = false) String roleName,
	    	@RequestParam(value="userType", required = false) int userType,
			@RequestParam(value="start", required = false) int start,
			@RequestParam(value="limit", required = false) int limit,
			HttpServletRequest request) {
    	User currentUser = userUtils.getUser(LoginType.admin, request);
		Group group = groupService.findByUserIdAndUserType(currentUser.getId(), Admin.USER_TYPE).get(0);
	
		//分页查询
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", roleName);
		params.put("domainId", Domain.DOMAIN_ADMIN);
		
		PageBounds pager = new PageBounds(start, limit);
		params.put("pager", pager);
		PageResult<Role> roleListResult = roleService.findForPage(params);
		
		int total = roleListResult.getPager().getTotalCount();
		//判断登录用户是否为超级管理员
		boolean isSuper = false;
		if(group.getType()!=Group.SUPER_GROUP_TYPE) {
			isSuper = false;
			removeSuperRole(roleListResult.getPageList());
			total -= 1; 
	    } else {
	    	isSuper = true;
	    }
		
		//设置组名显示前20个字符
		List<RoleVo> roleVoList = new ArrayList<RoleVo>();
		for(Role role : roleListResult.getPageList()) {
			RoleVo vo = new RoleVo();
			if(role.getRemark() != null) {
				role.setRemark(cutTooLongString(role.getRemark()));
			}	
			if(role.getName()!=null) {
				role.setName(cutTooLongString(role.getName()));
			}
			vo.setRole(role);
			if(role.getType() == Role.SUPER_ROLE_TYPE) {
				vo.setSuperRole(true);
			} else {
				vo.setSuperRole(false);
			}
			roleVoList.add(vo);
		}
		Integer pageTotal = 0;
		if(total == 0) {
			pageTotal = 0;
		} else {
			pageTotal = ((total-1) / PagerSize.USER) + 1 ;
		}
    	JSONObject json = new JSONObject();
    	json.put("roleVoList", roleVoList);
 	    json.put("currentPage", start);
 	    json.put("totalPage", pageTotal);
 	    json.put("totalCount", total);
 	    json.put("isSuper", isSuper);
 	    return json.toString(); 
    }
    /**
     * 
     * 移除超级管理员角色
     * 
     * */
    private List<Role> removeSuperRole(List<Role> roleList){
    	if(roleList!=null && roleList.size()>0) {
    		for(int i=0; i<roleList.size(); i++) {
    			Role role = roleList.get(i);
    			if(role.getType() == Role.SUPER_ROLE_TYPE) {
    				roleList.remove(i);
    				i --;
    			}
    		}
    		
    	}
    	return roleList;
    }
    /**
	 * 获得功能信息集合的所有ID.
	 * 
	 * @param functionList
	 *            功能信息集合
	 * @return 功能信息ID集合
	 */
	private List<Long> getfuncIdList(
			List<Function> functionList) {
		List<Long> funcIdList = new ArrayList<Long>();
		for (Function function : functionList) {
			funcIdList.add(function.getId());
		}

		return funcIdList;
	}
}
