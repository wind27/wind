package com.noriental.security.dao;

import java.util.List;
import java.util.Map;

import com.noriental.security.domain.Role;
import com.sumory.mybatis.pagination.result.PageResult;

/**
 * 角色信息数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public interface RoleDao {

	Role create(Role role);

	boolean delete(Long id);

	boolean updateRole(Role role);

	PageResult<Role> findForPage(Map<String, Object> params);

	Role findById(Long id);

	List<Role> find(Map<String, Object> params);

	List<Role> findByGroupId(Long groupId);

}
