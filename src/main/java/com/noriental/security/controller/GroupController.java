package com.noriental.security.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.noriental.security.usermanage.domain.GroupLinkUser;
import com.noriental.security.usermanage.domain.GroupVo;
import com.noriental.security.usermanage.domain.Permission;
import com.noriental.security.usermanage.domain.Role;
import com.noriental.security.usermanage.service.AdminService;
import com.noriental.security.usermanage.service.FunctionService;
import com.noriental.security.usermanage.service.GroupLinkRoleService;
import com.noriental.security.usermanage.service.GroupLinkUserService;
import com.noriental.security.usermanage.service.GroupService;
import com.noriental.security.usermanage.service.PermissionService;
import com.noriental.security.usermanage.service.RoleService;
import com.noriental.security.utils.PermissionUtils;
import com.noriental.security.utils.UserUtils;
import com.sumory.mybatis.pagination.result.PageResult;



@Controller
@RequestMapping("/user")
public class GroupController {

	@Autowired
	private GroupLinkUserService groupLinkUserService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private FunctionService functionService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private GroupLinkRoleService groupLinkRoleService;
	
	@Autowired
	private UserUtils userUtils;
	@Autowired
	private PermissionUtils permissionUtils;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	PermissionService permissionService;	
	
	/**
     * 
     * 打开添加组页面
     * 
     * */
    @RequestMapping("/openAddGroup")
    public String openAddGroup(ModelMap model, HttpSession session) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	List<Role> roleList = roleService.find(params);
    	
    	model.addAttribute("roleList", roleList);
        return "/user/addGroup";
    }

    /**
     * 
     * 添加组
     * 
     * */
    @ResponseBody
    @RequestMapping(value="/doAddGroup", method = RequestMethod.POST)
    public boolean  doAddGroup(ModelMap model, 
            @RequestParam(value = "roleIds", required = false) Long[] roleIds,
            Group group, HttpServletRequest request) {
    	group.setDomainId(Domain.DOMAIN_ADMIN);
    	group.setType(Group.COMMON_GROUP_TYPE);
    	group.setStats(Group.GROUP_STATS_ENABLE);
    	
    	return groupService.create(group, roleIds);
    }
    
    /**
     * 
     * 打开组列表
     * 
     * */
    @RequestMapping(value="/toGroupList", method=RequestMethod.GET)
    public String toGroupListPage(ModelMap model,
    		@RequestParam(value="pageNo", defaultValue="1", required=false) int pageNo,
    		HttpServletRequest request) {
    	model.addAttribute("pageNo", pageNo);
    	return "/user/groupList";
    }
    /**
     * 
     * 打开查看组页面
     * 
     * */
    @RequestMapping("/showGroup")
    public String showGroup(ModelMap model, 
    		@RequestParam("groupId") Long groupId,
    		HttpServletRequest request) {
    	Group group = groupService.findById(groupId);
    	
    	List<Role> roleList = roleService.find(null);
    	List<Role> selectedRoleList = roleService.findByGroupId(groupId);
    	List<Role> unSelectedRoleList = new ArrayList<Role>();
    	for(Role r : roleList) {
    		boolean flag = false;
    		for(Role tmp : selectedRoleList) {
    			if(r.getId().toString().equals(tmp.getId().toString())) {
    				flag = true;
    				break;
    			}
    		}
    		if(flag == false) {
    			unSelectedRoleList.add(r);
    		}
    	}
    	group.setRemark(cutTooLongString(group.getRemark()));
    	model.addAttribute("group", group);
    	model.addAttribute("unSelectedRoleList", unSelectedRoleList);
    	model.addAttribute("selectedRoleList", selectedRoleList);
    	return "/user/showGroup";
    }
    
    /**
     * 
     * 打开编辑组页面
     * 
     * */
    @RequestMapping("/openUpdateGroup")
    public String openUpdateGroup(ModelMap model, 
    		@RequestParam("groupId") Long groupId,
    		HttpServletRequest request) {
    	Group group = groupService.findById(groupId);
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("stats", Group.GROUP_STATS_ENABLE);
    	List<Role> roleList = roleService.find(params);
    	List<Role> selectedRoleList = roleService.findByGroupId(groupId);
    	List<Role> unSelectedRoleList = new ArrayList<Role>();
    	for(Role r : roleList) {
    		boolean flag = false;
    		for(Role tmp : selectedRoleList) {
    			if(r.getId().toString().equals(tmp.getId().toString())) {
    				flag = true;
    				break;
    			}
    		}
    		if(flag == false) {
    			unSelectedRoleList.add(r);
    		}
    	}
    	model.addAttribute("group", group);
    	model.addAttribute("unSelectedRoleList", unSelectedRoleList);
    	model.addAttribute("selectedRoleList", selectedRoleList);
    	
        return "/user/updateGroup";
    }
    
    /**
     * 
     * 更新组信息
     * 
     * */
    @ResponseBody
    @RequestMapping(value="/doUpdateGroup", method=RequestMethod.POST)
    public boolean doUpdateGroupFunction(ModelMap model, 
            Group group,
            @RequestParam(value = "roleIds", required = false) Long[] roleIds) {
    	boolean flag = groupService.updateGroup(group, roleIds);
    	
		//判断组信息是否已存储到redis中
		List<Function> funcList = null;
		List<Long> funcIds = null;
		List<Permission> permissList = null;
		
		funcList = functionService.findByGroupId(group.getId());
		funcIds = getfuncIdList(funcList);
		permissList = permissionService.findByFuncIds(funcIds);			// 获得用户能访问的所有功能资源
		
		permissionUtils.setFuncIds(group.getId(), funcIds);
		permissionUtils.setPermission(group.getId(), permissList);

    	return flag;
    }
    
    /**
     * 
     * 判断待新增的组名是否已存在
     * 
     * */
    @ResponseBody
    @RequestMapping(value="/valGroupName", method=RequestMethod.GET)
    public Boolean valGroupName(@RequestParam("groupname") String groupname, HttpSession session)
            throws UnsupportedEncodingException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", groupname.trim());
        List<Group> groupList = groupService.find(params);
        if(groupList!=null && groupList.size()>0) {
        	return false;
        }
        return true;
    }
	/**
	 * 
	 * 判断待删除的组中是否还有用户
	 * 
	 * */
	@ResponseBody
    @RequestMapping("/hasPersonInGroup")
    public boolean hasPersonInGroup(@RequestParam("groupId") Long groupId, HttpSession session)
            throws UnsupportedEncodingException {
		
		GroupLinkUser gu = new GroupLinkUser();
		gu.setGroupId(groupId);
		List<GroupLinkUser> guList = groupLinkUserService.find(gu);
		if(guList!=null && guList.size()>0) {
			return true;
		}
        return false;
    }
	/**
	 * 
	 * 删除组
	 * 
	 * */
	@ResponseBody
	@RequestMapping("/doDeleteGroup")
    public boolean doDeleteGroup(@RequestParam("groupId") Long groupId, HttpSession session)
            throws UnsupportedEncodingException {

		return groupService.delete(groupId);

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
    //分页--------------------------------------------------------------------------------
    /**
     * 
     * 打开用户列表
     * 
     * */
    @RequestMapping(value="/toGroupList", method=RequestMethod.POST,headers = {"Accept=application/json"})
    public @ResponseBody
    	Object tmp(ModelMap model, 
    			@RequestParam(value = "groupName", required = false) String groupName,
        		@RequestParam(value="userType", required = false) int userType,
        		@RequestParam(value="start", required = false) int start,
        		@RequestParam(value="limit", required = false) int limit,
        		HttpServletRequest request) {
    	boolean isSuper = false;
    	User currentUser = userUtils.getUser(LoginType.admin, request);
    	Group group = groupService.findByUserIdAndUserType(currentUser.getId(), Admin.USER_TYPE).get(0);
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("name", groupName);
    	params.put("domainId", Domain.DOMAIN_ADMIN);
    	params.put("stats", Group.GROUP_STATS_ENABLE);
    	
    	PageBounds pager = new PageBounds(start, limit);
    	params.put("pager", pager);
    	
    	PageResult<Group> groupListResult = groupService.findForPage(params);
    	List<Group> groupList = groupListResult.getPageList();
    	int total = groupListResult.getPager().getTotalCount();
		//判断登录用户是否为超级管理员
		if(group.getType()!=Group.SUPER_GROUP_TYPE) {
			groupList = removeSuperGroup(groupListResult.getPageList());
			total -= 1; 
			isSuper = false;
		} else {
			isSuper = true;
		}

    	//封装vo设置组名显示前20个字符
		List<GroupVo> groupVoList = new ArrayList<GroupVo>();
    	for(Group g : groupList) {
    		GroupVo vo = new GroupVo();
    		
    		if(g.getRemark() != null) {
    			g.setRemark(cutTooLongString(g.getRemark()));
    		}	
    		if(g.getName()!=null) {
    			g.setName(cutTooLongString(g.getName()));
    		}
    		vo.setGroup(g);
    		
    		if(g.getType()==Group.SUPER_GROUP_TYPE) {
        		vo.setSuperGroup(true);
        	} else {
        		vo.setSuperGroup(false);
        	}
    		groupVoList.add(vo);
    	}
    	
    	Integer pageTotal = 0;
		if(total == 0) {
			pageTotal = 0;
		} else {
			pageTotal = ((total-1) / PagerSize.USER) + 1 ;
		}
		
    	JSONObject json = new JSONObject();
    	json.put("groupVoList", groupVoList);
 	    json.put("currentPage", start);
 	    json.put("totalPage", pageTotal);
 	    json.put("totalCount", total);
 	    json.put("isSuper", isSuper);
 	    return json.toString(); 
    }
    
    /**
	 * 获得功能信息集合的所有ID.
	 * 
	 * @param functionList
	 *            功能信息集合
	 * @return 功能信息ID集合
	 */
	private List<Long> getfuncIdList(
			List<Function> funcList) {
		List<Long> funcIdList = new ArrayList<Long>();
		for (Function function : funcList) {
			funcIdList.add(function.getId());
		}

		return funcIdList;
	}
    /**
     * 
     * 移除超级管理员组
     * 
     * */
    private List<Group> removeSuperGroup(List<Group> groupList){
    	if(groupList!=null && groupList.size()>0) {
    		for(int i=0; i<groupList.size(); i++) {
    			Group g = groupList.get(i);
    			if(g.getType() == Group.SUPER_GROUP_TYPE) {
    				groupList.remove(i);
    				i --;
    			}
    		}
    		
    	}
    	return groupList;
    }
}
