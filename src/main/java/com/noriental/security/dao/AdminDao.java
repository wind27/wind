package com.noriental.security.dao;

import java.util.List;
import java.util.Map;

import com.noriental.security.domain.Admin;
import com.sumory.mybatis.pagination.result.PageResult;

/**
 * 人员信息数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public interface AdminDao {
	List<Admin> find(Map<String, Object> params);

	Admin findById(Long id);

	Admin create(Admin admin);

	boolean updateAdmin(Admin admin);

	boolean updateAdmin(Map<String, Object> params);


	boolean updatepasswd(Map<String, Object> params);


	PageResult<Admin> findForPage(Map<String, Object> params);
	
	List<Admin> findByGroupId(Long groupId);

	List<Admin> findByRoleId(Long id);

	int findSuperNum(Map<String, Object> params);
}
