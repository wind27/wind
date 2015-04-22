package com.noriental.security.dao;

import java.util.List;
import java.util.Map;

import com.noriental.security.domain.Permission;


/**
 * 功能资源数据操作.
 * 
 * @author 钱春
 * @version 1.0
 * 
 */
public interface PermissionDao {

	List<Permission> findByFuncIds(List<Long> funcIds);

	List<Permission> findByGroupId(Long groupId);

	boolean create(Permission Permission);

	boolean delete(Long id);

	Permission findById(Long id);

	boolean updatePermission(Permission permission);

	List<Permission> findPermissionForPage(Map<String, Object> params);

	Integer countPermissionForPage(Map<String, Object> params);
}
