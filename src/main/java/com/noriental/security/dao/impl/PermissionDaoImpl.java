package com.noriental.security.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.noriental.common.BaseDaoImpl;
import com.noriental.security.dao.PermissionDao;
import com.noriental.security.domain.Permission;


/**
 * 功能资源数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<Permission, Long> implements PermissionDao  {
	private final String namespace = Permission.class.getName();
	
	@Override
	public List<Permission> findByFuncIds(List<Long> funcIds) {
		return super.findList(namespace+".findByFuncIds", funcIds);
	}

	@Override
	public List<Permission> findByGroupId(Long groupId) {
		return super.findList(namespace+".findByGroupId", groupId);
	}

	@Override
	public boolean create(Permission Permission) {
		return super.insert(Permission);
	}

	@Override
	public boolean delete(Long id) {
		return super.delete(Permission.class, id);
	}

	@Override
	public Permission findById(Long id) {
		return super.findById(Permission.class, id);
	}

	@Override
	public boolean updatePermission(Permission permission) {
		return super.update(permission);
	}

	@Override
	public List<Permission> findPermissionForPage(
			Map<String, Object> params) {
		PageBounds pageBounds = (PageBounds) params.get("pager");
		return super.findList(namespace+".findPermissionForPage", params);
//		return super.findPagedList(namespace+".findPermissionForPage", params, pageBounds);
	}

	@Override
	public Integer countPermissionForPage(Map<String, Object> params) {
		return super.findList(namespace+".countPermissionForPage", params).size();
	}
}
