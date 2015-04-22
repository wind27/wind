package com.noriental.security.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.noriental.common.BaseDaoImpl;
import com.noriental.security.dao.RoleDao;
import com.noriental.security.domain.Role;
import com.sumory.mybatis.pagination.result.PageResult;

/**
 * 角色信息数据操作.
 * 
 * @author 肖诚
 * @version 1.0
 * 
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao{
	private final String namespace = Role.class.getName();
	
	@Override
	public Role create(Role role) {
		return super.insertGetId(role);
	}

	@Override
	public boolean delete(Long id) {
		return super.delete(Role.class, id);
	}

	@Override
	public boolean updateRole(Role role) {
		return super.update(role);
	}

	@Override
	public PageResult<Role> findForPage(Map<String, Object> params) {
		PageBounds pageBounds = (PageBounds) params.get("pager");
		return super.findPageResult(namespace+".findForPage", params, pageBounds);
	}

	@Override
	public Role findById(Long id) {
		return super.findById(Role.class, id);
	}

	@Override
	public List<Role> find(Map<String, Object> params) {
		return super.findList(namespace+".find", params);
	}

	@Override
	public List<Role> findByGroupId(Long groupId) {
		return super.findList(namespace+".findByGroupId", groupId);
	}

}
