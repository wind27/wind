package com.noriental.security.service;


import java.util.List;
import java.util.Map;

import com.noriental.security.domain.Role;
import com.sumory.mybatis.pagination.result.PageResult;

/**
 * 角色信息业务操作接口.
 * 
 * @author 肖诚
 * @version 1.0
 * 
 */
public interface RoleService {

	boolean delete(Long id);

	boolean create(Role role, Long[] funclist);

	PageResult<Role> findForPage(Map<String, Object> params);

	Role findById(Long roleId);

	boolean updateRole(Role role, Long[] funclist);

	List<Role> find(Map<String, Object> params);

	List<Role> findByGroupId(Long groupId);
}
