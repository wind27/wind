package com.noriental.security.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.noriental.common.BaseDaoImpl;
import com.noriental.security.dao.AdminDao;
import com.noriental.security.domain.Admin;
import com.sumory.mybatis.pagination.result.PageResult;

/**
 * 人员信息数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
@Repository("adminDao")
public class AdminDaoImpl extends BaseDaoImpl<Admin, Long> implements AdminDao{
	private final String namespace=Admin.class.getName();
	
	@Override
	public List<Admin> find(Map<String, Object> params) {
		
		return super.findList(namespace+".find", params);
		
	}
	@Override
	public Admin findById(Long id) {
		return super.findById(Admin.class, Long.parseLong(id.toString()));
	}
	@Override
	public Admin create(Admin admin) {
		return super.insertGetId(admin);
	}
	@Override
	public boolean updateAdmin(Admin admin) {
		return super.update(admin);
	}
	@Override
	public boolean updateAdmin(Map<String, Object> params) {
		return super.update(namespace+".update", params);
	}
	@Override
	public boolean updatepasswd(Map<String, Object> params) {
		return super.update(namespace+".updatepasswd", params);
	}
	@Override
	public PageResult<Admin> findForPage(Map<String, Object> params) {
		PageBounds pageBounds = (PageBounds) params.get("pager");
		return super.findPageResult(namespace+".findForPage", params, pageBounds);
	}
	@Override
	public List<Admin> findByGroupId(Long groupId) {
		return super.findList(namespace+".findByGroupId", groupId);
	}
	@Override
	public List<Admin> findByRoleId(Long roleId) {
		return super.findList(namespace+".findByRoleId", roleId);
	}
	@Override
	public int findSuperNum(Map<String, Object> params) {
		List<Admin> adminList = super.findList(namespace+".findSuperNum", params);
		if(adminList!=null && adminList.size()>0) {
			return adminList.size();
		}
		return 0;
	}
}
