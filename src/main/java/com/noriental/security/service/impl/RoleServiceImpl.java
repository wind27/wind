package com.noriental.security.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.noriental.security.dao.FunctionDao;
import com.noriental.security.dao.GroupDao;
import com.noriental.security.dao.PermissionDao;
import com.noriental.security.dao.RoleDao;
import com.noriental.security.dao.RoleLinkFuncDao;
import com.noriental.security.domain.Role;
import com.noriental.security.domain.RoleLinkFunc;
import com.noriental.security.service.RoleService;
import com.sumory.mybatis.pagination.result.PageResult;

/**
 * 角色信息业务操作接口实现.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired(required = true)
	private RoleDao roleDao;

	@Autowired
	private RoleLinkFuncDao roleFuncDao;
	
	@Autowired
	private FunctionDao functionDao;
	
	@Autowired
	private PermissionDao funcResourceDao;
	
	@Autowired
	private GroupDao groupDao;
	
	
	
	
	@Override
	public boolean create(Role role, Long[] funclist) {
		boolean flag = true;
		//创建角色
		role = roleDao.create(role);
		
		if(role==null || role.getId()==null) {
			logger.error("添加组信息：添加角色信息失败！！！");
			flag = false;
		}
	  	
	  	// 添加组与权限的关系
	  	RoleLinkFunc rf = null;
	  	List<RoleLinkFunc> rflist = new ArrayList<RoleLinkFunc>();
	  	if(funclist!=null && funclist.length>0) {
	  		for (Long funcId : funclist) {
	  			rf = new RoleLinkFunc();
	  			rf.setFuncId(funcId);
	  			rf.setRoleId(role.getId());
	  			rflist.add(rf);
	  		}
	  	}
	  	
	  	if(rflist!=null && rflist.size()>0) {
	  		flag = roleFuncDao.batchCreate(rflist);
	  	}
	
		return flag;
	}

	@Override
	public boolean delete(Long id) {
		boolean flag = true;
		// 获取跟组关联的权限信息id
		List<RoleLinkFunc> roleFuncList = roleFuncDao.findByRoleId(id);
		List<Long> roleIds = new ArrayList<Long>();
		if(roleFuncList != null && roleFuncList.size()>0) {
			for(RoleLinkFunc rf : roleFuncList) {
				if(rf!=null && rf.getRoleId()!=null) {
					roleIds.add(rf.getRoleId());
				}
			}
		}
        // 删除角色关联权限信息
        flag = roleFuncDao.batchDeleteByRoleIds(roleIds);
        
		// 删除角色信息
		flag = roleDao.delete(id);

		return flag;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public PageResult<Role> findForPage(Map<String, Object> params) {
		return roleDao.findForPage(params);
	}

	@Override
	public Role findById(Long id) {
		return roleDao.findById(id);
	}

	@Override
	public boolean updateRole(Role role, Long[] funclist) {
		boolean flag = true;
		
		//删除与组-权限信息
		List<RoleLinkFunc> roleFuncList = roleFuncDao.findByRoleId(role.getId());
        List<Long> roleIds = new ArrayList<Long>();
		if(roleFuncList != null && roleFuncList.size()>0) {
			for(RoleLinkFunc rf : roleFuncList) {
				if(rf!=null && rf.getRoleId()!=null) {
					roleIds.add(rf.getRoleId());
				}
			}
		}
        flag = roleFuncDao.batchDeleteByRoleIds(roleIds);
        
        // 添加组与功能的关系
        RoleLinkFunc rf = null;
        List<RoleLinkFunc> rflist = new ArrayList<RoleLinkFunc>();
        if(funclist!=null && funclist.length>0) {
        	for (Long funcId : funclist) {
        		rf = new RoleLinkFunc();
        		rf.setFuncId(funcId);
        		rf.setRoleId(role.getId());
        		rflist.add(rf);
        	}
        	flag = roleFuncDao.batchCreate(rflist);
        }
        
        flag = roleDao.updateRole(role);
        
		return flag;
	}
	
	@Override
	public List<Role> find(Map<String, Object> params) {
		return roleDao.find(params);
	}

	@Override
	public List<Role> findByGroupId(Long groupId) {
		return roleDao.findByGroupId(groupId);
	}
}
