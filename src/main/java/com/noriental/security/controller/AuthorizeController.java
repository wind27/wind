package com.noriental.security.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.zookeeper.ZooDefs.Perms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.noriental.global.Constants.PagerSize;
import com.noriental.security.usermanage.domain.Domain;
import com.noriental.security.usermanage.domain.Function;
import com.noriental.security.usermanage.domain.Group;
import com.noriental.security.usermanage.domain.Permission;
import com.noriental.security.usermanage.service.FunctionService;
import com.noriental.security.usermanage.service.GroupService;
import com.noriental.security.usermanage.service.PermissionService;
import com.noriental.security.utils.PermissionUtils;

@Controller
@RequestMapping("/auth")
public class AuthorizeController {
    @Autowired
    private PermissionService permissionService;
    
    @Autowired
    private FunctionService functionService;
    
    @Autowired
    private PermissionUtils permissionUtils;

    @Autowired
    private GroupService groupService;
    
    
    private static final Logger logger = LoggerFactory.getLogger(AuthorizeController.class);
    	
	/**
	 * 跳转功能模块页面
	 * 
	 * @author qianchun  @date 2015年3月20日 上午10:22:05
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/func/index")
	public String index(ModelMap model, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		return "/user/funcList";
	}
	/**
     * 功能模块列表
     * 
     * @author qianchun  @date 2015年3月20日 上午10:23:16
     * @param model
     * @param id
     * @param domain
     * @param userType
     * @param authType
     * @param start
     * @param limit
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/func/list", method=RequestMethod.GET,
            headers = {"Accept=application/json"})
    public 
    @ResponseBody
    Object getFunc(ModelMap model, 
            @RequestParam("id") Long id,
            @RequestParam("domain") Integer domain,
            @RequestParam("status") Integer status,
            @RequestParam(required=false,defaultValue="1") Integer start,
            @RequestParam(required=false,defaultValue=PagerSize.USER+"") Integer limit,
            HttpServletRequest request, 
            HttpServletResponse response) {
        PageBounds pageBounds = new PageBounds(start, limit);
        
        Map<String, Object> params = new HashMap<>();
        if(status != -1) {
            params.put("stats", status);
        }
        
        params.put("pager", pageBounds);
        params.put("parentCode", id);
        params.put("domain", domain);
        params.put("start", (start-1)*30);
        params.put("size", limit);
        
        List<Function> funcList = (List<Function>) functionService.findFuncForPage(params);
        Integer total = functionService.countFuncForPage(params);
        
        Integer pageTotal = 0;
        if(total == 0) {
            pageTotal = 0;
        } else {
            pageTotal = ((total-1) / PagerSize.USER) + 1 ;
        }
        
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("funcList", funcList);
        data.put("currentPage", start);
        data.put("totalPage", pageTotal);
        data.put("totalCount", total);
        return data;
    }
	//-----------------------------------------------------------------功能模块添加----------
	/**
	 * 跳转添加功能模块页面
	 * 
	 * @author qianchun  @date 2015年3月20日 上午10:21:33
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/func/add", method=RequestMethod.GET)
    public String addFunc(ModelMap model, 
            HttpServletRequest request, 
            HttpServletResponse response) {
        return "/user/addFunc";
    }
	/**
     * 添加功能模块
     * 
     * @author qianchun  @date 2015年3月20日 上午10:24:02
     * @param model
     * @param func
     * @param request
     * @param response
     * @return
     */
	@ResponseBody
    @RequestMapping(value="/func/add", method=RequestMethod.POST,
            headers = {"Accept=application/json"})
    public Object doAddFunc(ModelMap model, 
            Function func,
            HttpServletRequest request, 
            HttpServletResponse response) {
        String _orderTmp = request.getParameter("_order");
        func.setStats(Function.FUNCTION_STATS_ENABLE);
        func.setUserType(1);
        func.setDomainId(Domain.DOMAIN_ADMIN);
        
        
        if(_orderTmp!=null && _orderTmp.length()>0) {
            func.set_order(Integer.parseInt(_orderTmp));
        } 
        boolean flag = true;
        func = functionService.create(func);
        if(func!=null && func.getId()>0) {
            flag = true;
            updateAuthorizeInRedis();
        } else {
            flag = false;
        }
        return "{success:"+flag+"}";
    }
	
    //-----------------------------------------------------------------功能模块启用/停用----------
    @ResponseBody
    @RequestMapping(value="/func/enableOrDisable", method=RequestMethod.POST, headers = {"Accept=application/json"})
    public Object enableOrDisableFunc(ModelMap model, 
            @RequestParam("id") Long id,
            @RequestParam("stats") int stats,
            @RequestParam("type") int type,
            HttpServletRequest request, 
            HttpServletResponse response) {
        List<Long> funcIds = new ArrayList<Long> ();
        
        
        Map<String, Object> data = new HashMap<>();
        funcIds.add(id);
        funcIds.addAll(getFuncIds(getFuncList(id)));
        
        boolean flag = functionService.updateStatsByfuncIds(funcIds, stats);
        if(flag == true) {
            updateAuthorizeInRedis();
        }
        data.put("success", flag);
        return data;
    }
    //-----------------------------------------------------------------功能模块编辑----------
    /**
     * 编辑功能模块
     * 
     * @author qianchun  @date 2015年3月20日 上午10:29:16
     * @param model
     * @param id
     * @param type
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/func/edit", method=RequestMethod.GET)
    public String editFunc(ModelMap model, 
            @RequestParam("id") Long id,
            @RequestParam("type") int type,
            HttpServletRequest request, 
            HttpServletResponse response) {
        
        Function func = functionService.findById(id);
        String parentName = functionService.findById(func.getParentCode()).getName();
        
        model.addAttribute("func", func);
        model.addAttribute("parentModel", type+"级:"+parentName);
        return "/user/updateFunc";
    }
    
    /**
     * 修改功能模块
     * 
     * @author qianchun  @date 2015年3月20日 上午10:29:51
     * @param model
     * @param func
     * @param id
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/func/update", method=RequestMethod.POST, headers = {"Accept=application/json"})
    public Object updateFunc(ModelMap model, 
            Function func,
            @RequestParam("id") Long id,
            HttpServletRequest request, 
            HttpServletResponse response) {
        String _orderTmp = request.getParameter("_order");
        
        if(_orderTmp!=null && _orderTmp.length()>0) {
            func.set_order(Integer.parseInt(_orderTmp));
            
        } 
        boolean flag = functionService.updateFunction(func);
        if(flag == true) {
            updateAuthorizeInRedis();
        }

        Map<String, Object> data = new HashMap<>();
        data.put("success", flag);
        return data;
    }
    //-----------------------------------------------------------------功能模块编辑----------
    private List<Function> getFuncList(Long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentCode", id);

        List<Function> result = new ArrayList<Function>();
        List<Function> funcList = functionService.find(params);
        
        result.addAll(funcList);
        
        if(funcList!=null && funcList.size()>0) {
            for(int i=0; i<funcList.size(); i++) {
                Function info = funcList.get(i);
                if(info!=null && info.getId()>0) {
                    List<Function> tmp = getFuncList(funcList.get(i).getId());
                    if(tmp!=null) {
                        result.addAll(tmp);
                    }
                }
            }
        } else {
            return null;
        }
        return result;
    }
    private List<Long> getFuncIds(List<Function> funcList) {
        List<Long> funcIds = new ArrayList<Long>();
        if(funcList!=null && funcList.size()>0) {
            for(Function info : funcList) {
                if(info!=null && info.getId()>0) {
                    funcIds.add(info.getId());
                }
            }
        }
        return funcIds;
    }
    
    
    //-------------------------------------------------------------------------------------------
    
    
    /**
     * 打开查询权限页面
     * 
     * @author qianchun  @date 2015年3月20日 下午1:51:29
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/permission/index", method=RequestMethod.GET)
    public String funcResourList(ModelMap model, 
            HttpServletRequest request, 
            HttpServletResponse response) {
        return "/user/permissionList";
    }
  
    /**
     * 权限列表查询
     * 
     * @author qianchun  @date 2015年3月20日 下午1:51:55
     * @param model
     * @param funcId
     * @param start
     * @param limit
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/permission/list", method=RequestMethod.GET, headers = {"Accept=application/json"})
    public Object getFuncResour(ModelMap model, 
            @RequestParam("funcId") Long funcId,
            @RequestParam(required=false,defaultValue="1") Integer start,
            @RequestParam(required=false,defaultValue=PagerSize.USER+"") Integer limit,
            HttpServletRequest request, 
            HttpServletResponse response) {
        // 分页查询
        List<Long> funcIds = new ArrayList<Long>();
        funcIds.add(funcId);
        
        Map<String, Object> params = new HashMap<String, Object>();
        PageBounds pageBounds = new PageBounds(start, limit);
        params.put("pager", pageBounds);
        params.put("funcIds", funcIds);
        params.put("start", (start-1)*30);
        params.put("size", limit);
        
        List<Permission> permissionList = (List<Permission>) permissionService.findPermissionForPage(params);
        Integer total = permissionService.countPermissionForPage(params);
        
        Integer pageTotal = 0;
        if(total == 0) {
            pageTotal = 0;
        } else {
            pageTotal = ((total-1) / PagerSize.USER) + 1 ;
        }
        
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("funcId", funcId);
        data.put("permissionList", permissionList);
        data.put("currentPage", start);
        data.put("totalPage", pageTotal);
        data.put("totalCount", total);
        return data; 
    }
    
    /**
     * 打开添加权限页面
     * 
     * @author qianchun  @date 2015年3月20日 下午1:51:09
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/permission/add", method=RequestMethod.GET)
    public String addPermission(HttpServletRequest request, 
            HttpServletResponse response) {
        return "/user/addPermission";
    }
    /**
     * 添加权限
     * 
     * @author qianchun  @date 2015年3月20日 下午1:52:15
     * @param model
     * @param permission
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/permission/add", method=RequestMethod.POST, headers = {"Accept=application/json"})
    public Object addPermission(ModelMap model,
            Permission permission,
            HttpServletRequest request, 
            HttpServletResponse response) {
        permission.setStats(1);
        boolean flag = permissionService.create(permission);
        
        if(flag == true) {
            updateAuthorizeInRedis();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("success", flag);
        return data;
    }
    
    /**
     * 打开编辑权限页面
     * 
     * @author qianchun  @date 2015年3月20日 下午1:53:27
     * @param model
     * @param id
     * @param type
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/permission/edit", method=RequestMethod.GET)
    public String editPermission(ModelMap model, 
            @RequestParam("id") Long id,
            @RequestParam("type") int type,
            HttpServletRequest request, 
            HttpServletResponse response) {
        Permission permission = permissionService.findById(id);
        Function function = functionService.findById(permission.getFuncId());
        
        model.addAttribute("permission", permission);
        model.addAttribute("parentModel", type+"级:"+function.getName());
        return "/user/updatePermission";
    }

    /**
     * 修改权限
     * 
     * @author qianchun  @date 2015年3月20日 下午1:54:03
     * @param model
     * @param permission
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/permission/update", method=RequestMethod.POST, headers = {"Accept=application/json"})
    public Object updatePermission(ModelMap model, 
            Permission permission,
            HttpServletRequest request, 
            HttpServletResponse response) {
        boolean flag = permissionService.updatePermission(permission);
        if(flag == true) {
            updateAuthorizeInRedis();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("success", flag);
        return data;
    }

    /**
     * 启用或者停用权限
     * 
     * @author qianchun  @date 2015年3月20日 下午1:49:26
     * @param model
     * @param id
     * @param flag
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/permission/enableOrDisable", method=RequestMethod.POST, headers = {"Accept=application/json"})
    public Object enableOrDisablePermission(ModelMap model, 
            @RequestParam("id") Long id,
            @RequestParam("status") int status,
            HttpServletRequest request, 
            HttpServletResponse response) {
        Permission permission = permissionService.findById(id);
        permission.setStats(status);
        boolean flag = permissionService.updatePermission(permission);
        if(flag == true) {
            updateAuthorizeInRedis();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("success", flag);
        return data;
    }
    
    private void updateAuthorizeInRedis() {
        List<Group> groupList = groupService.find(null);
        for(int i=0; i<groupList.size(); i++) {
            Group g = groupList.get(i);
            List<Permission> permissList = permissionService.findByGroupId(g.getId());
            permissionUtils.setPermission(g.getId(), permissList);
        }
    }
    
    @ResponseBody
    @RequestMapping(value="/permission/reset", method=RequestMethod.GET, headers = {"Accept=application/json"})
    public Object resetPermission(
            HttpServletRequest request, 
            HttpServletResponse response) {
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        List<Group> groupList = groupService.find(null);
        for(int i=0; i<groupList.size(); i++) {
            Group g = groupList.get(i);
            List<Permission> permissList = permissionService.findByGroupId(g.getId());
            permissionUtils.setPermission(g.getId(), permissList);
        }
        params.put("stats", Function.FUNCTION_STATS_ENABLE);
        params.put("domainId", Domain.DOMAIN_ADMIN);
        List<Function> funcList = functionService.find(params);
        
        List<Permission> permissList = permissionService.findByFuncIds(getFuncIds(funcList));
        permissionUtils.setPermission(1, permissList);
        data.put("success", true);
        return data;
    }
}
