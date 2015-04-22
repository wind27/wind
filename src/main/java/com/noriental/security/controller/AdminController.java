package com.noriental.security.controller;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.noriental.common.Constants;
import com.noriental.common.Constants.LoginType;
import com.noriental.common.Constants.PagerSize;
import com.noriental.security.domain.Admin;
import com.noriental.security.domain.AdminVo;
import com.noriental.security.domain.Group;
import com.noriental.security.domain.TStage;
import com.noriental.security.domain.TSubject;
import com.noriental.security.domain.User;
import com.noriental.security.domain.UserLinkSubject;
import com.noriental.security.service.AdminService;
import com.noriental.security.service.GroupService;
import com.noriental.security.service.TSubjectService;
import com.noriental.security.service.UserLinkSubjectService;
import com.noriental.utils.Encrypt;
import com.noriental.utils.UserUtils;
import com.sumory.mybatis.pagination.result.PageResult;

@Controller
@RequestMapping("/user")
public class AdminController {
    @Autowired(required = true)
    private AdminService adminService;

    @Autowired
    private UserLinkSubjectService userLinkSubjectService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private TSubjectService tSubjectService;

	@Autowired
	private UserUtils userUtils;
    
    /**
     * 
     * 打开添加用户的页面
     * 
     * */
    @RequestMapping("/openAddPerson")
    public String openAddPerson(ModelMap model, 
    		HttpServletRequest request) {
    	User currentUser = userUtils.getUser(LoginType.admin, request);
    	Group group = groupService.findByUserIdAndUserType(currentUser.getId(), Admin.USER_TYPE).get(0);
    	Map<String, Object> params = new HashMap<String, Object>();
        params.put("stats", 0);
        List<Group> groupList = groupService.find(params);
        if(group.getType()!=Group.SUPER_GROUP_TYPE) {
    		groupList = removeSuperGroup(groupList);
    	}
        
        List<TSubject> juniorSchool = new ArrayList<TSubject>();
        List<TSubject> primarySchool = new ArrayList<TSubject>();
        List<TSubject> seniorSchool = new ArrayList<TSubject>();
        
        setSubjectList(primarySchool, juniorSchool, seniorSchool);
        
        model.addAttribute("juniorSchool", juniorSchool);
        model.addAttribute("primarySchool", primarySchool);
        model.addAttribute("seniorSchool", seniorSchool);
        
        model.addAttribute("groupList", groupList);
        model.addAttribute("admin", new Admin());
        return "/user/addAdmin";
    }
    
    /**
     * 
     * 添加用户
     * 
     * */
    @ResponseBody
    @RequestMapping(value="/doAddPerson", method=RequestMethod.POST)
    public boolean doAddPerson(ModelMap model, HttpServletRequest request,
    		@RequestParam(value = "primarySubject", required = false) Long[] primarySubject,
    		@RequestParam(value = "juniorSubject", required = false) Long[] juniorSubject,
    		@RequestParam(value = "seniorSubject", required = false) Long[] seniorSubject,
    		@RequestParam("groupId") Long groupId, 
    		Admin admin) {
    	
    	Group group = groupService.findById(groupId);
    	admin.setStats(Admin.ADMIN_STATS_ENABLE);
    	admin.setType(Admin.COMMON_ADMIN_TYPE);
    	admin.setCreateTime(new Date());
    	
    	
    	return adminService.create(admin, groupId, group.getDomainId(), primarySubject, juniorSubject, seniorSubject);
    }
    
    /**
     * 
     * 打开用户列表
     * 
     * */
    @RequestMapping(value="/toPersonList", method=RequestMethod.GET)
    public String toPersonList(ModelMap model, 
    		@RequestParam(value="pageNo", defaultValue="1", required=false) int pageNo,
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	model.addAttribute("pageNo", pageNo);
        return "/user/adminList";
    }
    /**
     * 
     * 打开用户更新页面
     * @param userId 	：	待更新用户id
     * @param groupId	：	待更新用户所属组id
     * 
     * 
     * */
    @RequestMapping("/openUpdatePerson")
    public String openUpdateUser(ModelMap model, 
    		@RequestParam("userId") Long userId,
            @RequestParam("groupId") Long groupId, 
            HttpServletRequest request, 
            HttpServletResponse response) {
    	
    	User currentUser = userUtils.getUser(LoginType.admin, request);
    	Group currentGroup = groupService.findByUserIdAndUserType(currentUser.getId(), Admin.USER_TYPE).get(0);
        
    	//获取待修改用户信息
        Admin admin = adminService.findById(userId);
        model.addAttribute("admin", admin);
        //获取待修改用户组信息
        Group group = groupService.findById(groupId);
        model.addAttribute("group", group);

        //获取所有学科学段信息
        List<TSubject> juniorSchool = new ArrayList<TSubject>();
        List<TSubject> primarySchool = new ArrayList<TSubject>();
        List<TSubject> seniorSchool = new ArrayList<TSubject>();
        setSubjectList(primarySchool, juniorSchool, seniorSchool);
        model.addAttribute("juniorSchool", juniorSchool);
        model.addAttribute("primarySchool", primarySchool);
        model.addAttribute("seniorSchool", seniorSchool);
        
        //获取已选择的学科学段
        List<UserLinkSubject> pls = userLinkSubjectService.findByUserIdAndUserType(userId, Admin.USER_TYPE);
        //获取用户已选学科信息
        List<Long> selectedJuniorSubject = new ArrayList<Long>();
        List<Long> selectedPrimarySubject= new ArrayList<Long>();
        List<Long> selectedSeniorSubject = new ArrayList<Long>();
        for(UserLinkSubject ps : pls) {
        	if(ps.getGradeType() == TStage.PRIMARY_SCHOOL) {
        		selectedPrimarySubject.add(ps.getSubjectId());
        	} else if(ps.getGradeType() == TStage.SENIOR_MIDDLE_SCHOOL) {
        		selectedSeniorSubject.add(ps.getSubjectId());
        	} else if(ps.getGradeType() == TStage.JUNIOR_HIGH_SCHOOL) {
        		selectedJuniorSubject.add(ps.getSubjectId());
        	}
        }
        model.addAttribute("selectedJuniorSubject",selectedJuniorSubject);
        model.addAttribute("selectedSeniorSubject", selectedSeniorSubject);
        model.addAttribute("selectedPrimarySubject", selectedPrimarySubject);
        if(selectedPrimarySubject != null && selectedPrimarySubject.size()>0) {
        	model.addAttribute("primarySubs", true);
        } else {
        	model.addAttribute("primarySubs", false);
        }
        if(selectedJuniorSubject != null && selectedJuniorSubject.size()>0) {
        	model.addAttribute("juniorSubs", true);
        } else {
        	model.addAttribute("juniorSubs", false);
        }
        if(selectedSeniorSubject != null && selectedSeniorSubject.size()>0) {
        	model.addAttribute("seniorSubs", true);
        } else {
        	model.addAttribute("seniorSubs", false);
        }
        
      //获取组列表信息
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("stats", 0);
        List<Group> grouplist = groupService.find(params);
        if(currentGroup.getType()!=Group.SUPER_GROUP_TYPE) {
        	grouplist = removeSuperGroup(grouplist);
    	}
        model.addAttribute("grouplist", grouplist);
        return "/user/updateAdmin";
    }
    /**
     * 
     * 打开用户更新页面
     * @param userId 	：	待更新用户id
     * @param groupId	：	待更新用户所属组id
     * 
     * 
     * */
    @RequestMapping("/showPerson")
    public String showUser(ModelMap model, 
    		@RequestParam("userId") Long userId,
            @RequestParam("groupId") Long groupId, 
            HttpServletRequest request,
    		HttpServletResponse response,
    		HttpSession session) {
    	//获取待修改用户信息
    	Admin admin = adminService.findById(userId);
        model.addAttribute("admin", admin);
        //获取待修改用户组信息
        Group group = groupService.findById(groupId);
        model.addAttribute("group", group);

        //获取所有学科学段信息
        List<TSubject> juniorSchool = new ArrayList<TSubject>();
        List<TSubject> primarySchool = new ArrayList<TSubject>();
        List<TSubject> seniorSchool = new ArrayList<TSubject>();
        
        setSubjectList(primarySchool, juniorSchool, seniorSchool);
        
        model.addAttribute("juniorSchool", juniorSchool);
        model.addAttribute("primarySchool", primarySchool);
        model.addAttribute("seniorSchool", seniorSchool);
        
        //获取已选择的学科学段
        List<UserLinkSubject> usList = userLinkSubjectService.findByUserIdAndUserType(userId, Admin.USER_TYPE);
        //获取用户已选学科信息
        List<Long> selectedJuniorSubject = new ArrayList<Long>();
        List<Long> selectedPrimarySubject= new ArrayList<Long>();
        List<Long> selectedSeniorSubject = new ArrayList<Long>();
        for(UserLinkSubject us : usList) {
        	if(us.getGradeType() == TStage.PRIMARY_SCHOOL) {
        		selectedPrimarySubject.add(us.getSubjectId());
        	} else if(us.getGradeType() == TStage.SENIOR_MIDDLE_SCHOOL) {
        		selectedSeniorSubject.add(us.getSubjectId());
        	} else if(us.getGradeType() == TStage.JUNIOR_HIGH_SCHOOL) {
        		selectedJuniorSubject.add(us.getSubjectId());
        	}
        }
        model.addAttribute("selectedJuniorSubject",selectedJuniorSubject);
        model.addAttribute("selectedSeniorSubject", selectedSeniorSubject);
        model.addAttribute("selectedPrimarySubject", selectedPrimarySubject);
        if(selectedPrimarySubject != null && selectedPrimarySubject.size()>0) {
        	model.addAttribute("primarySubs", true);
        } else {
        	model.addAttribute("primarySubs", false);
        }
        if(selectedJuniorSubject != null && selectedJuniorSubject.size()>0) {
        	model.addAttribute("juniorSubs", true);
        } else {
        	model.addAttribute("juniorSubs", false);
        }
        if(selectedSeniorSubject != null && selectedSeniorSubject.size()>0) {
        	model.addAttribute("seniorSubs", true);
        } else {
        	model.addAttribute("seniorSubs", false);
        }
        //获取组列表信息
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("stats", 0);
    	List<Group> groupList = groupService.find(params);					//获取所有组信息
        model.addAttribute("grouplist", groupList);
    	
        return "/user/showAdmin";
    }

    /**
     * 
     * 更新用户信息
     * @param gradetype :	年级id
     * @param subjectid ：	学科ids
     * @param groupId	：	组id
     * 
     * 
     * */
	@ResponseBody
    @RequestMapping(value="/doUpdatePerson", method=RequestMethod.POST)
    public Object doUpdatePerson(ModelMap model, Admin admin, 
    		@RequestParam(value = "primarySubject", required = false) Long[] primarySubject,
    		@RequestParam(value = "juniorSubject", required = false) Long[] juniorSubject,
    		@RequestParam(value = "seniorSubject", required = false) Long[] seniorSubject,
    		@RequestParam("groupId") Long groupId,
    		HttpServletRequest request) {
		
		boolean flag = adminService.updateAdmin(admin, groupId, primarySubject, juniorSubject, seniorSubject);
		
    	Map<String, Object> data = new HashMap<String, Object>();
    	data.put("result", flag);
    	return data;
    }
    
    /**
     * 
     * 判断待新增的用户的登录名是否已存在
     * 
     * */
    @RequestMapping("/valLoginUser")
    @ResponseBody
    public Boolean valLoginUser(ModelMap model, @RequestParam("loginUser") String loginUser) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("loginUser", loginUser);
    	Admin admin = null;
		List<Admin> adminList = adminService.find(params);
		if(adminList==null || adminList.size()>0) {
			admin = adminList.get(0);
		}
        if (admin != null && admin.getId() != null)
            return false;
        else
            return true;
    }

    /**
     * 
     * 启用用户
     * 
     * */
    @RequestMapping("/doEnablePerson")
    public @ResponseBody
	boolean doEnablePerson(ModelMap model, @RequestParam("userId") Long userId,
            @RequestParam("groupId") String groupId) {
    	Admin admin = new Admin();
    	admin.setId(userId);
    	admin.setStats(Admin.ADMIN_STATS_ENABLE);
    	return adminService.updateAdmin(admin);
    }
    
   	/**
   	 * 
   	 * 停用用户
   	 * 
   	 * */
   @RequestMapping("/doDisablePerson")
   public @ResponseBody
   	boolean  doDisablePerson(ModelMap model, @RequestParam("userId") Long userId,
           @RequestParam("groupId") String groupId) {
	   	Admin admin = new Admin();
	   	admin.setId(userId);
	   	admin.setStats(1);
	   	return adminService.updateAdmin(admin);
   }
   
   
   /**
	 * 
	 * 修改密码
	 * 
	 * */
	@RequestMapping("/doUpdatePwd")
   public @ResponseBody int doUpdatePwd(ModelMap model, 
   		@RequestParam("loginPass") String loginPass, HttpServletRequest request) {
	    User currentUser = userUtils.getUser(LoginType.admin, request);
		Admin admin = adminService.findById(currentUser.getId());
		Encrypt encrypt = new Encrypt();
		loginPass = encrypt.encrypt(loginPass, "MD5");
		try {
			loginPass = Base64.encodeBase64String(loginPass.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginPass", loginPass);
		params.put("loginUser", admin.getLoginUser());
		return adminService.updatepasswd(params) == true ? 1 : 0;
   }
	/**
	 * 
	 * 修改密码前，测试输入的原始密码是否正确
	 * 
	 * */
	@RequestMapping(value="/checkPasswd", method = RequestMethod.GET)
	public @ResponseBody
	int checkPasswd(@RequestParam(value = "loginPass") String loginPass, HttpServletRequest request) {
		User currentUser = userUtils.getUser(LoginType.admin, request);
		Encrypt encrypt = new Encrypt();
		loginPass = encrypt.encrypt(loginPass, "MD5");
		try {
			loginPass = Base64.encodeBase64String(loginPass.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Admin admin = adminService.findById(currentUser.getId());
		
		return admin.getLoginPass().equals(loginPass) == true ? 0 : -1;
	}
	/**
	 * 
	 * 重置密码
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/updatepwd", method=RequestMethod.POST)
	public int updatepassword(HttpServletRequest request) {
		String loginUser = request.getParameter("loginUser");
		Map<String, Object> params = new HashMap<String, Object>();

		Encrypt encrypt = new Encrypt();
		String  loginPass = encrypt.encrypt("123456"+Constants.Md5Key.PORTAL_ADMIN, "MD5");
		loginPass = encrypt.encrypt(loginPass, "MD5");
		
		try {
			loginPass = Base64.encodeBase64String(loginPass.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		params.put("loginUser", loginUser);
		params.put("loginPass", loginPass);
		return adminService.updatepasswd(params) == true ? 1 : 0;
	}
//-----------------------------------------------------------------------------------------------------	
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
     * 设置学科信息
     * 
     * */
    private void setSubjectList(List<TSubject> primarySchool, 
    		List<TSubject> juniorSchool,
    		List<TSubject> seniorSchool) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("ids", null);
        List<TSubject> subjectList = tSubjectService.find(params);
        
        for(TSubject ts : subjectList) {
        	if(ts.getStageId() == TStage.PRIMARY_SCHOOL) {
        		primarySchool.add(ts);
        	} else if(ts.getStageId() == TStage.JUNIOR_HIGH_SCHOOL) {
        		juniorSchool.add(ts);
        	} else if(ts.getStageId() == TStage.SENIOR_MIDDLE_SCHOOL) {
        		seniorSchool.add(ts);
        	}
        }
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
    
    /**
     * 
     * 打开用户列表
     * 
     * */
    @RequestMapping(value="/toPersonList", method=RequestMethod.POST,headers = {"Accept=application/json"})
    public @ResponseBody
    	Object tmp(ModelMap model, 
    		@RequestParam(value = "start", required = false, defaultValue="1") int start,
    		@RequestParam(value = "limit", required = false, defaultValue="30") int limit,
    		@RequestParam(value = "groupId", required = false) long groupId,
    		@RequestParam(value = "name", required = false) String name,
    		@RequestParam(value = "stats", required = false) int stats,
    		HttpServletRequest request) {
    	User currentUser = userUtils.getUser(LoginType.admin, request);
    	Group group = groupService.findByUserIdAndUserType(currentUser.getId(), Admin.USER_TYPE).get(0);
    	
    	Map<String, Object> params = new HashMap<String, Object>();
        params.put("stats", 0);
    	List<Group> groupList = groupService.find(params);					//获取所有组信息
    	
    	if(group.getType()!=Group.SUPER_GROUP_TYPE) {
    		groupList = removeSuperGroup(groupList);
    	}

    	//获取所有组的ids
    	List<Long> groupIds = new ArrayList<Long>();
    	if(groupId == -1l) {
    		for(Group g : groupList) {
    			if(g!=null&&g.getId()!=null) {
    				groupIds.add(g.getId());
    			}
    		}
    	} else {
    		groupIds.add(groupId);
    	}
    	
    	params = new HashMap<String, Object>();
    	
    	params.put("groupIds", groupIds);
    	params.put("name", name);
    	params.put("loginUser", name);
    	params.put("stats", stats);

    	PageBounds pager = new PageBounds(start, limit);
    	params.put("pager", pager);

    	PageResult<Admin> adminListResult = adminService.findForPage(params);
    	
    	
    	int superNum = adminService.findSuperNum(params);
    	
        //装配 adminVo
        List<AdminVo> adminVoList = new ArrayList<AdminVo>();
        for (Admin admin : adminListResult.getPageList()) {
        	AdminVo vo = new AdminVo();
        	if(admin.getName() != null) {
        		admin.setName(cutTooLongString(admin.getName()));
    		}	
        	Group g = groupService.findByUserIdAndUserType(admin.getId(), Admin.USER_TYPE).get(0);
        	if(g!=null && g.getName()!=null) {
    			g.setName(cutTooLongString(g.getName()));
        	}
        	
        	
        	if(group.getType()!= Group.SUPER_GROUP_TYPE && g.getType()==Group.SUPER_GROUP_TYPE) {
        		continue;
        	} else if(g.getType()==Group.SUPER_GROUP_TYPE) {
        		vo.setSuper(true);
        	} else {
        		vo.setSuper(false);
        	}
        	
        	vo.setAdmin(admin);
        	vo.setGroup(g);
        	adminVoList.add(vo);
        }
    	//是否为超级管理员
        int total = adminListResult.getPager().getTotalCount();
    	boolean isSuper = false;
    	if(group.getType()==Group.SUPER_GROUP_TYPE) {
    		isSuper = true;
        } else {
        	total -= superNum; 
        	isSuper = false;
        }
    	
    	Integer pageTotal = 0;
    	if(total == 0) {
    		pageTotal = 0;
    	} else {
    		pageTotal = ((total-1) / PagerSize.USER) + 1 ;
    	}
    	
    	JSONObject json = new JSONObject();
    	json.put("adminVoList", adminVoList);
 	    json.put("currentPage", start);
 	    json.put("totalPage", pageTotal);
 	    json.put("totalCount", total);
 	    json.put("isSuper", isSuper);
 	    return json.toString(); 
    }
    
    @RequestMapping(value="/getGroup", method=RequestMethod.GET,headers = {"Accept=application/json"})
    public @ResponseBody
    	Object getFunc(ModelMap model, 
    			HttpServletRequest request) {
    	
    	User currentUser = userUtils.getUser(LoginType.admin, request);
    	Group group = groupService.findByUserIdAndUserType(currentUser.getId(), Admin.USER_TYPE).get(0);
    	
    	List<Group> groupList = groupService.find(null);
    	
    	 if(group.getType()!=Group.SUPER_GROUP_TYPE) {
     		groupList = removeSuperGroup(groupList);
     	}
    	
    	JSONObject json = new JSONObject();
    	json.put("groupList", groupList);
 	    return json.toString(); 
	}
}
