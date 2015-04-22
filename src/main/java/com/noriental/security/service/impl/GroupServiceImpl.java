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
import com.noriental.security.dao.GroupLinkRoleDao;
import com.noriental.security.dao.PermissionDao;
import com.noriental.security.dao.RoleDao;
import com.noriental.security.dao.RoleLinkFuncDao;
import com.noriental.security.domain.Function;
import com.noriental.security.domain.Group;
import com.noriental.security.domain.GroupLinkRole;
import com.noriental.security.service.GroupService;
import com.sumory.mybatis.pagination.result.PageResult;

/**
 * 功能模块信息业务操作接口实现.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService {

	private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleLinkFuncDao roleFuncDao;
	
	@Autowired
	private FunctionDao functionDao;
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private GroupLinkRoleDao groupLinkRoleDao;
	
	/**
	 * 
	 * 根据条件查询组
	 * 
	 * */
	@Override
	public List<Group> find(Map<String, Object> params) {
		return groupDao.find(params);
	}

	/**
	 * 
	 * 更新组信息
	 * 
	 * @param : rolelist	组的新的角色ids
	 * 
	 * 
	 * */
	@Override
	public boolean updateGroup(Group group, Long[] roleIds) {
		boolean flag = true;
		
		//删除与组-角色信息, 并更新组-角色信息
		flag = groupLinkRoleDao.deleteByGroupId(group.getId());
		
		List<GroupLinkRole> grList = new ArrayList<GroupLinkRole>();
		if(roleIds!=null && roleIds.length>=0) {
			for(Long roleId : roleIds) {
				GroupLinkRole gr = new GroupLinkRole();
				gr.setGroupId(group.getId());
				gr.setRoleId(roleId);
				grList.add(gr);
			}
		}
		if(grList!=null && grList.size()>0) {
			flag = groupLinkRoleDao.batchCreate(grList);
		}
		
		//更新组信息
		flag = groupDao.updateGroup(group);
		return flag;
	}

	/**
	 * 
	 * 根据用户id获取对应的组
	 * 
	 * */
	@Override
	public List<Group> findByUserIdAndUserType(Long userId, int userType) {
		return groupDao.findByUserIdAndUserType(userId, userType);
	}

	/**
	 * 
	 * 分页查询
	 * 
	 * */
	@Override
	public PageResult<Group> findForPage(Map<String, Object> params) {
		return groupDao.findForPage(params);
	}
	/**
	 * 
	 * 创建组
	 * 
	 * */
	@Override
	public boolean create(Group group, Long[] roleIds) {
		boolean flag = true;
		
		//创建组
	  	group = groupDao.create(group);

	  	// 添加组与角色的关系
	  	GroupLinkRole gr = null;
	  	List<GroupLinkRole> grList = new ArrayList<GroupLinkRole>();
	  	
	  	if(roleIds!=null && roleIds.length>0) {
	  		for(Long roleId : roleIds) {
	  			gr = new GroupLinkRole();
	  			gr.setGroupId(group.getId());
	  			gr.setRoleId(roleId);
	  			grList.add(gr);
	  		}
	  		flag = groupLinkRoleDao.batchCreate(grList);
	  	}
		
		return flag;
	}

	/**
	 * 
	 * 根据组id获取组信息
	 * 
	 * */
	@Override
	public Group findById(Long groupId) {
		return groupDao.findById(groupId);
	}

	/**
	 * 
	 * 删除组
	 * 
	 * */
	@Override
	public boolean delete(Long groupId) {
		boolean flag = true;

		//删除组-角色关系
		flag = groupLinkRoleDao.deleteByGroupId(groupId);
        
		//删除组信息
		flag = groupDao.delete(groupId);
		
		return flag;
	}

	/**
     * 
	 * 获得功能信息集合的所有ID.
	 * 
	 * @param funcList : 功能信息集合
	 * @return 功能信息ID集合
	 * 
	 */
	private List<Long> getfuncIdsList(
			List<Function> funcList) {
		List<Long> funcIds = new ArrayList<Long>();
		for (Function function : funcList) {
			funcIds.add(function.getId());
		}

		return funcIds;
	}

	public GroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public RoleLinkFuncDao getRoleFuncDao() {
		return roleFuncDao;
	}

	public void setRoleFuncDao(RoleLinkFuncDao roleFuncDao) {
		this.roleFuncDao = roleFuncDao;
	}

	public FunctionDao getFunctionDao() {
		return functionDao;
	}

	public void setFunctionDao(FunctionDao functionDao) {
		this.functionDao = functionDao;
	}

	public PermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	public GroupLinkRoleDao getGroupLinkRoleDao() {
		return groupLinkRoleDao;
	}

	public void setGroupLinkRoleDao(GroupLinkRoleDao groupLinkRoleDao) {
		this.groupLinkRoleDao = groupLinkRoleDao;
	}
}
